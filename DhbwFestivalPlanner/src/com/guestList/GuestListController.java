package com.guestList;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helper.RandomString;
import com.model.Event;
import com.model.Guest;
import com.model.GuestList;
import com.model.User;
import com.model.wrapper.GuestListIdWrapper;

@Controller
@RequestMapping("/guestList")
public class GuestListController {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Zeigt das Gaestelisten Formular an
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/show")
	public String show(Model model,
			@RequestParam(value = "id", required = true) int id) {
		model.addAttribute("eventId",id );
		
		GuestList guestList = new GuestList();
		guestList.setItems(getEvent(id).getGuests());
		model.addAttribute("guestList", guestList);

		return "event/inviteguests";
	}

	/**
	 * Aktualisiert die Gaesteliste der Veranstaltung
	 *
	 * @param model
	 * @param id
	 * @param guestList
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			Model model,
			@RequestBody GuestListIdWrapper guestListIdWrapper) {

		// INITIALISIERUNG
		List<Guest> internalGuestList = guestListIdWrapper.getGuestList().getItems();
		int id = guestListIdWrapper.getId();
		List<Guest> currentGuestList = getEvent(id).getGuests();
		List<Guest> dirtyGuests = new ArrayList<Guest>();
		Event currentEvent = getEvent(id);
		// END INITIALISIERUNG

		// Fuer alle Gaeste in der neuen Gaesteliste
		for (Guest guestToCheck : internalGuestList) {

			// Event mappen
			guestToCheck.setEvent(currentEvent);

			// Ueberpruefen ob ein Gast nur abgeaendert wurde, falls ja kommt er
			// in die dirtyGuest Liste, um ihn spaeter zu updaten
			boolean allreadyInList = checkIfExistingGuest(currentGuestList,
					dirtyGuests, guestToCheck);

			// Wenn Gast noch nicht in der Liste
			if (!allreadyInList) {
				// Restliche Gaeste zu dirtyGuests hinzufuegen
				dirtyGuests.add(guestToCheck);
			}

		}

		// Dirty guests gegen Datenbank abgleichen und uebrige Gaeste entfernen
		updateDatabase(dirtyGuests, currentGuestList);

		return "guestList/inviteguests";
	}

	/**
	 * Looks for all guests from an event and send an invitation to all guests
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/invite")
	public String invite(@RequestParam(value = "id", required = true) int id) {
		Event event = getEvent(id);

		for (Guest g : event.getGuests()) {
			sendInvitationMail(event, g);
		}

		updateInvitedGuests(event.getGuests());

		return "";
	}

	/**
	 * Sends an invitation email to a given guest from a given event
	 * 
	 * @param event the event
	 * @param guest the guest which should receive the email
	 */
	private void sendInvitationMail(Event event, Guest guest) {
		if (guest.isReceivesEmail() && !guest.isReceivedInvitation()) {

			RandomString randomString = new RandomString(40);
			guest.setEventCode(randomString.nextString());
			
			SimpleMailMessage message = new SimpleMailMessage();

			message.setFrom("info@dhbwfestivalplanner.de");
			message.setTo(guest.getEmail());
			message.setSubject("Einladung zu: " + event.getName());
			message.setText("Du wurdest zu einer Veranstaltung eingeladen! Du findest sie unter folgendem Link: "
					+ "\n http://festivalplanner.de/event/guestView?id="
					+ event.getId()
					+ "&gid="
					+ guest.getId()
					+ "&gcode="
					+ guest.getEventCode());
			mailSender.send(message);

			guest.setReceivedInvitation(true);
		}
	}

	/**
	 * Ueberprueft ob ein Gast schon in der Gaesteliste ist, wenn er geaendert
	 * wurde kommt er in die dirtyList
	 *
	 * @param currentGuests
	 * @param dirtyGuests
	 * @param guest
	 * @return
	 */
	protected boolean checkIfExistingGuest(List<Guest> currentGuests,
			List<Guest> dirtyGuests, Guest guest) {

		// Pruefen welche bestehenden Gaeste abgeaendert wurden
		for (Guest guestInGuestList : currentGuests) {

			if (guestInGuestList.getId() == guest.getId()) {
				if (!guestInGuestList.softEquals(guest)) {

					// Felder aktualisieren
					dirtyGuests.add(guestInGuestList);
				}

				// Gast entfernen, damit nicht nochmal geprueft wird
				currentGuests.remove(guestInGuestList);
				return true;
			}

		}
		return false;
	}

	/**
	 * Aktualisiert und fuegt die geaenderten Gaeste der Datenbank hinzu und
	 * loescht die Gaeste, die entfernt wurden
	 *
	 * @param dirtyList
	 * @param currentGuestList
	 */
	protected void updateDatabase(List<Guest> dirtyList,
			List<Guest> currentGuestList) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (Guest dG : dirtyList) {
			session.saveOrUpdate(dG);
		}
		for (Guest oldG : currentGuestList) {
			session.delete(oldG);
		}
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Aktualisiert die Gaeste, die eine Einladungs-Email erhalten haben
	 * 
	 * @param invitedGuests
	 */
	protected void updateInvitedGuests(List<Guest> invitedGuests) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (Guest g : invitedGuests) {
			session.update(g);
		}
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Holt das aktuelle event anhand der ID aus der Datenbank
	 * 
	 * @param id
	 * @return
	 */
	protected Event getEvent(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Event event = (Event) session.get(Event.class, id);
		session.getTransaction().commit();
		session.close();
		return event;
	}
	

}





