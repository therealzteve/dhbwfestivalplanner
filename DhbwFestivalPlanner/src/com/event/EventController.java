package com.event;

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

import com.helper.UserHelper;
import com.model.Event;
import com.model.User;

@Controller
@RequestMapping("/event")
public class EventController {

	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping("/create")
	public String create(Model model) {
		return "event/create";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	public String save(
			Model model,
			@RequestParam(value = "id", required = false, defaultValue = "-1") int id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "plz", required = false, defaultValue = "0") int plz,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "time", required = false) String time)
			throws Exception {
		
		User user = UserHelper.getCurrentUser();

		Date parsedDate = null;
		Date parsedTime = null;
		try {
			parsedDate = parseDate(date);
			parsedTime = parseTime(time);

		} catch (Exception e) {

			Event event = getEvent(id, user, false);
			fillEventData(event, title, name, address, city, plz);

			model.addAttribute("event", event);
			model.addAttribute("dateInvalid",true);
			return "event/edit";
		}

		Event event = getEvent(id, user, false);

		if (event == null) {
			throw new Exception("Event with id: " + id
					+ " not found. Cancelling request.");
		}

		// Set event data
		fillEventData(event, title, name, address, city, plz);
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
			@RequestParam(value = "id", required = true, defaultValue = "-1") int id) {

		Event event = getEvent(id, UserHelper.getCurrentUser(), false);

		if (id == -1) {
			return null;
		}
		model.addAttribute("event", event);
		Hibernate.initialize(event);

		return event;
	}

	@RequestMapping("/edit")
	public String edit(
			Model model,
			@RequestParam(value = "id", required = true, defaultValue = "-1") int id) {

		Event event = getEvent(id, UserHelper.getCurrentUser(), false);

		model.addAttribute("event", event);
		model.addAttribute("time", formatTime(event.getTime()));
		model.addAttribute("date", formatDate(event.getDate()));
 
		return "event/edit";
	}

	@RequestMapping(value = { "/guestView", "/guestview" })
	public String guestView(
			Model model,
			@RequestParam(value = "id", required = true, defaultValue = "-1") int id) {

		if (id != -1) {
			Event event = getEvent(id, null, true);
			if (event == null) {
				return "event/guestViewError";
			}
			model.addAttribute("event", event);
		} else {
			return "event/guestViewError";
		}

		return "event/guestView";
	}

	private Event getOrCreateEvent(Session session, int id, User user,
			boolean guest) {
		Event event;
		if (id != -1) {
			event = (Event) session.get(Event.class, id);

			// If given user is not the owner of the event, return null for
			// security reasons
			if (guest == false && event != null
					&& event.getCreator().getId() != user.getId()) {
				return null;
			}
		} else {
			event = new Event();
		}
		return event;
	}

	private void fillEventData(Event event, String title, String name,
			String address, String city, int plz) {
		event.setTitle(title);
		event.setName("Testort");
		event.setAddress(address);
		event.setCity(city);
		event.setPlz(plz);
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

	private Event getEvent(int id, User user, boolean guest) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Event event = getOrCreateEvent(session, id, user, guest);
		session.getTransaction().commit();
		session.close();
		return event;
	}

	private String formatTime(Date date) {
		if (date == null) {
			return "";
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String hours = "" + c.get(Calendar.HOUR_OF_DAY);
		String minutes = "" + c.get(Calendar.MINUTE);
		
		//Add leading zero
		if(minutes.length() == 1){
			minutes = "0"+ minutes;
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
}