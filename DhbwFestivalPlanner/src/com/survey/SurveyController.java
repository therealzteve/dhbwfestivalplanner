package com.survey;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.factory.EventFactory;
import com.factory.SurveyFactory;
import com.model.Event;
import com.model.Survey;

@Controller
@RequestMapping("/survery")
public class SurveyController {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	SurveyFactory surveyFactory;



	@RequestMapping("/display")
	@ResponseBody
	public Survey getSurvey(Model model, @RequestParam("survey") int surveyId, @RequestParam("event") int eventId) {
		Survey survey = surveyFactory.getSurvey(surveyId, eventId);
		return survey;
	}

	@RequestMapping("/edit")
	public String edit(
			Model model,
			@RequestParam("event") int eventId,
			@RequestParam(value = "survey", required = false, defaultValue = "0") int surveyId) {

		Survey survey = surveyFactory.getSurvey(surveyId, eventId);

		model.addAttribute("survey", survey);

		return "survey/edit";
	}

	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Model model, Survey survey){
		
		//TODO validation
		
		// Save survey in database
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(survey);
		session.getTransaction().commit();
		
		return "survey/saved";
	}

}
