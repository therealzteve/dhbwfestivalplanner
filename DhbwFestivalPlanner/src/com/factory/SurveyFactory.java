package com.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.model.Event;
import com.model.Survey;

public class SurveyFactory {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	EventFactory eventFactory;

	public Survey getSurvey(int id, int eventId) {
		if (id != 0) {
			Survey survey = loadSurvey(id);
			if(rightEvent(survey, eventId)){
				return survey;
			}
			return null;
		}
		return createSurvey(eventId);
	}

	private boolean rightEvent(Survey survey, int eventId) {
		Event event = eventFactory.getEvent(eventId, false);
		
		if(event.getId() == survey.getEvent().getId()){
			return true;
		}
		return false;
	}

	public Survey loadSurvey(int id) {
		Survey survey;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		survey = (Survey) session.get(Survey.class, id);
		session.getTransaction().commit();
		session.close();
		
		
		return survey;

	}

	private Survey createSurvey(int eventId) {
		Survey survey = new Survey();
		survey.setEvent(eventFactory.getEvent(eventId, false));
		return survey;
	}
	
	
}
