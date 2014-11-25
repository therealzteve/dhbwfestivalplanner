package com.event;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.helper.UserHelper;
import com.model.Event;
import com.model.User;

@Controller
@RequestMapping("/event")
public class EventController {

	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping("/helloWorld")
	public String hello(Model model) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Event> events = session.createQuery("from Event").list();
		session.getTransaction().commit();
		session.close();
		for (Event ev : events) {
			System.out.println(ev);
		}
		model.addAttribute("events", events);

		return "event/result";
	}

	@RequestMapping("/create")
	public String create(Model model) {
		return "event/create";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			Model model,
			@RequestParam(value = "id", required = false, defaultValue = "-1") int id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "plz", required = false) int plz,
			@RequestParam(value = "city", required = false) int city,
			@RequestParam(value = "date", required = false) Date date,
			@RequestParam(value = "time", required = false) Date time)
			throws Exception {
		Session session = sessionFactory.openSession();
		User user = UserHelper.getCurrentUser();

		Event event = null;
		if (id != -1) {
			event = (Event) session.get(Event.class, id);

			// This should not technically happen, otherwise the user changed
			// violently the form.
			if (event.getCreator().getId() != user.getId()) {
				throw new Exception(
						"A user tried to change an event which is not his own. Access denied.");
			}

		} else {
			event = new Event();

		}

		event.setName("the new event");
		event.setCreator(user);

		session.beginTransaction();
		session.save(event);
		session.getTransaction().commit();

		model.addAttribute("event", event);

		return "event/list";
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
	public String display(
			Model model,
			@RequestParam(value = "id", required = true, defaultValue = "-1") int id,
			@RequestParam(value = "mode", required = false, defaultValue = "display") String mode) {

		if (id == -1) {
			return "error";
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Event event = (Event) session.get(Event.class, id);
		session.getTransaction().commit();
		session.close();

		model.addAttribute("event", event);
		if ("edit".equals(mode)) {
			return "event/edit";
		}
		return "event/display";
	}

}