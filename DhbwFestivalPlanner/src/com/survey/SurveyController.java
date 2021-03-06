package com.survey;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.factory.SurveyFactory;
import com.model.Survey;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	SurveyFactory surveyFactory;

	@RequestMapping("/display")
	/*@ResponseBody
	public Survey getSurvey(Model model, @RequestParam("survey") int surveyId,
			@RequestParam("event") int eventId) {
		Survey survey = surveyFactory.getSurvey(surveyId, eventId);
		return survey;*/
	//DEMO
	
	public String display(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") int id) {
		model.addAttribute("eventid",id);
		return "event/umfrage";
	}
	
	

	@RequestMapping("/edit")
	/*public String edit(
			Model model,
			@RequestParam("event") int eventId,
			@RequestParam(value = "survey", required = false, defaultValue = "0") int surveyId) {

		Survey survey = surveyFactory.getSurvey(surveyId, eventId);

		model.addAttribute("survey", survey);

		return "survey/edit";
	}*/
	//DEMO
	public String edit(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") int id) {
		model.addAttribute("eventid",id);
		return "event/umfrageerstellen";
	}
	
	//NOCH MEHR DEMO
	@RequestMapping("/update")
	//DEMO
	public String update(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") int id) {
		model.addAttribute("eventid",id);
		return "event/umfrage_refresh";
	}
	
	@RequestMapping("/vote")
	//DEMO
	public String vote(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") int id) {
		model.addAttribute("eventid",id);
		return "eventpage/umfrageuser";
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, Survey survey) {

		// TODO validation

		// Save survey in database
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(survey);
		session.getTransaction().commit();

		return "survey/saved";
	}

	@RequestMapping(value = "/increaseOption")
	public String increaseOption(Model model,
			@RequestParam("event") int eventId,
			@RequestParam("survey") int surveyId,
			@RequestParam("option") int optionId) {
		
		Survey survey = surveyFactory.getSurvey(surveyId, eventId);
		survey.increaseOption(optionId);
		return "survey/optAck";
	}

}
