package com.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;

import com.model.Survey;

public class SurveyFactory {

	@Autowired
	SessionFactory sessionFactory;
	
	public Survey getSurvey(int id){
		Survey survey = new Survey();
		return survey;
	}
}
