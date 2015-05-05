package com.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.helper.UserHelper;
import com.model.Event;

public class EventFactory {

	@Autowired
	private SessionFactory sessionFactory;

	public Event getEvent(int id, boolean guest) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Event event = getOrCreateEvent(session, id, guest);
		session.getTransaction().commit();
		session.close();
		return event;
	}

	private Event getOrCreateEvent(Session session, int id, boolean guest) {
		Event event;

		if (id != 0) {
			event = loadEvent(session, id);

			// If given user is not the owner of the event, return null for
			// security reasons
			if (guest == false && !hasPermition(event)) {
				return null;
			}
			return event;
		}

		event = new Event();
		return event;
	}

	private Event loadEvent(Session session, int id) {
		Event event = (Event) session.get(Event.class, id);
		return event;
	}

	private boolean hasPermition(Event event) {
		if (event.getCreator().getId() == UserHelper.getCurrentUser().getId()) {
			return true;
		}
		return false;
	}

}
