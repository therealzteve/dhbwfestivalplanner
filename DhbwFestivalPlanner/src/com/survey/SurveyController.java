package com.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.factory.SurveyFactory;
import com.model.Survey;

@Controller
@RequestMapping("/survery")
public class SurveyController {

	@Autowired
	SurveyFactory surveyFactory;
	
	@RequestMapping("/display")
	@ResponseBody
	public Survey getSurvey(Model model, @RequestParam("id") int surveyId){
		Survey survey = surveyFactory.getSurvey(surveyId);
		return survey;
	}
}
