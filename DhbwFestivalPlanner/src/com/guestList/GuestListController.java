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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Event;
import com.model.Guest;
import com.model.User;

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

		model.addAttribute("guestList", getEvent(id).getGuests());

		return "guestList/show";
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
			@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "guestList", required = true) List<Guest> guestList) {

		// INITIALISIERUNG
		List<Guest> currentGuestList = getEvent(id).getGuests();
		List<Guest> dirtyGuests = new ArrayList<Guest>();
		Event currentEvent = getEvent(id);
		// END INITIALISIERUNG

		// Fuer alle Gaeste in der neuen Gaesteliste
		for (Guest guestToCheck : guestList) {

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

		return "guestList/show";
	}

	/**
	 * Looks for all guests from an event and send an invitation to all guests
	 * which don't got an invitation and are not email blocked.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/invite")
	public String invite(@RequestParam(value = "id", required = true) int id) {
		Event event = getEvent(id);
		ArrayList<String> recipients = new ArrayList<String>();
		ArrayList<Guest> recipientsGuests = new ArrayList<Guest>();
		for (Guest g : event.getGuests()) {
			if (g.isReceivesEmail() && !g.isReceivedInvitation()) {
				recipients.add(g.getEmail());
				g.setReceivedInvitation(true);
				recipientsGuests.add(g);
			}
		}
		sendInvitationMail((String[]) recipients.toArray(), event.getTitle(), id);
		
		updateInvitedGuests(recipientsGuests);
		return "";
	}

	/**
	 * Sends an invitation email to all given recipients
	 * 
	 * @param to
	 */
	private void sendInvitationMail(String[] to, String eventName, int id) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("info@dhbwfestivalplanner.de");
		message.setTo(to);
		message.setSubject("Einladung zu: " + eventName);
		message.setText("Du wurdest zu einer Veranstaltung eingeladen! Du findest sie unter folgendem Link: "
				+ "\n http://festivalplanner.de/event/guestView?id="+id);
		mailSender.send(message);
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
