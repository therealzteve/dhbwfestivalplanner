package com.factory;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.collection.internal.PersistentBag;
import org.springframework.beans.factory.annotation.Autowired;

import com.helper.UserHelper;
import com.model.Budget;
import com.model.Event;

public class EventFactory {

	@Autowired
	private SessionFactory sessionFactory;

	public Event getEvent(int id, boolean guest) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Event event = getOrCreateEvent(session, id, guest);
		Hibernate.initialize(event.getBudget().getBudgetPositions());
		session.getTransaction().commit();
		
		
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
		Budget budget = new Budget();
		event.setBudget(budget);
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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
