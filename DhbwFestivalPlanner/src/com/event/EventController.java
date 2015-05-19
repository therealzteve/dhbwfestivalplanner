package com.event;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.factory.EventFactory;
import com.helper.UserHelper;
import com.model.Event;
import com.model.Guest;
import com.model.User;

@Controller
@RequestMapping("/event")
public class EventController {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private EventFactory eventFactory;

	@RequestMapping("/create")
	public String create(Model model) {
		return "event/create";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			Model model,
			@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "description", required = false, defaultValue = "") String description,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "plz", required = false, defaultValue = "0") int plz,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "time", required = false) String time,
			@RequestParam(value = "design", required = false, defaultValue = "0") int design)
			throws Exception {

		User user = UserHelper.getCurrentUser();

		Date parsedDate = null;
		Date parsedTime = null;
		try {
			parsedDate = parseDate(date);
			parsedTime = parseTime(time);

		} catch (Exception e) {
			// Invalid dates were tried to be parsed, return error

			Event event = eventFactory.getEvent(id, false);
			fillEventData(event, title, description, name, address, city, plz,
					design);

			model.addAttribute("event", event);
			model.addAttribute("dateInvalid", true);
			return "event/edit";
		}

		Event event = eventFactory.getEvent(id, false);

		if (event == null) {
			throw new Exception("Event with id: " + id
					+ " not found. Cancelling request.");
		}

		// Set event data
		fillEventData(event, title, description, name, address, city, plz,
				design);
		event.setCreator(user);
		event.setDate(parsedDate);
		event.setTime(parsedTime);

		// Save event in database
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(event);
		session.getTransaction().commit();

		model.addAttribute("event", event);

		return "event/saved";
	}

	@RequestMapping("/list")
	public String list(Model model) {

		// Aktueller User bestimmen
		User user = UserHelper.getCurrentUser();

		// Events aus Datenbank laden
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Event.class);
		crit.add(Restrictions.eq("creator", user));
		List<Event> ev = crit.list();
		session.getTransaction().commit();

		// Event Liste fuer JSP Seite
		model.addAttribute("events", ev);

		// aufgerufene JSP Seite
		return "event/list";
	}

	@RequestMapping("/display")
	public @ResponseBody Event display(
			Model model,
			@RequestParam(value = "id", required = true, defaultValue = "0") int id) {

		Event event = eventFactory.getEvent(id, false);

		if (id == 0) {
			return null;
		}
		model.addAttribute("event", event);
		Hibernate.initialize(event);

		return event;
	}

	@RequestMapping("/edit")
	public String edit(
			Model model,
			@RequestParam(value = "id", required = true, defaultValue = "0") int id) {

		Event event = eventFactory.getEvent(id, false);

		model.addAttribute("event", event);
		model.addAttribute("time", formatTime(event.getTime()));
		model.addAttribute("date", formatDate(event.getDate()));

		return "event/edit";
	}
	
	@RequestMapping(value = "/delete", method= RequestMethod.POST)
	public String delete(
			Model model,
			@RequestParam(value = "id", required = true, defaultValue = "0") int id) {

		Event event = eventFactory.getEvent(id, false);
		
		if(event == null){
			model.addAttribute("errorMessage", "Event mit id: " + id + " nicht gefunden.");
			return "event/error";
		}
		
		deleteEvent(event);
		
		return list(model);
	}

	@RequestMapping(value = { "/guestView", "/guestview" })
	public String guestView(
			Model model,
			@RequestParam(value = "id", required = true, defaultValue = "0") int id,
			@RequestParam(value = "gid", required = true, defaultValue = "0") int gid,
			@RequestParam(value = "gcode", required = true, defaultValue = "0") String gcode) {

		if (id != 0) {
			Event event = eventFactory.getEvent(id, true);
			Guest g = new Guest();
			g.setId(gid);
			g.setEventCode(gcode);

			if (isValidGuestEvent(event, g)) {
				return "event/guestViewError";
			}

			model.addAttribute("event", event);
		}

		return "event/guestView";
	}

	/**
	 * Checks if a guest is allowed to see an event.
	 * 
	 * @param event
	 * @param guest
	 * @return
	 */
	private boolean isValidGuestEvent(Event event, Guest guest) {
		if (event != null) {
			for (Guest g : event.getGuests()) {
				if (g.getId() == guest.getId()
						&& g.getEventCode().equals(guest.getEventCode())) {
					return true;
				}
			}
		}
		return false;
	}

	private void fillEventData(Event event, String title, String description,
			String name, String address, String city, int plz, int design) {
		event.setTitle(title);
		event.setDescription(description);
		event.setName("Testort");
		event.setAddress(address);
		event.setCity(city);
		event.setPlz(plz);
		event.setDesign(design);
	}

	private Date parseDate(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd.mm.yyyy");
		Date parsedDate = df.parse(date);
		return parsedDate;
	}

	private Date parseTime(String time) throws ParseException {
		String[] parsedTime = time.split(":");
		int hours = Integer.valueOf(parsedTime[0]);
		int minutes = Integer.valueOf(parsedTime[1]);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, hours);
		c.set(Calendar.MINUTE, minutes);
		return c.getTime();
	}

	private String formatTime(Date date) {
		if (date == null) {
			return "";
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String hours = "" + c.get(Calendar.HOUR_OF_DAY);
		String minutes = "" + c.get(Calendar.MINUTE);

		// Add leading zero
		if (minutes.length() == 1) {
			minutes = "0" + minutes;
		}
		return hours + ":" + minutes;
	}

	private String formatDate(Date date) {
		if (date == null) {
			return "";
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		SimpleDateFormat df = new SimpleDateFormat("dd.mm.yyyy");
		return df.format(date);
	}
	
	private void deleteEvent(Event event){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(event);
		session.getTransaction().commit();
	}

	public EventFactory getEventFactory() {
		return eventFactory;
	}

	public void setEventFactory(EventFactory eventFactory) {
		this.eventFactory = eventFactory;
	}
}