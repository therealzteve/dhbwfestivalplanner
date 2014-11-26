package com.main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.helper.UserHelper;
import com.model.Event;
import com.model.User;

@Controller
public class MainController {

	@Autowired
	private SessionFactory sessionFactory;
	
	@RequestMapping("/")
	public String main(Model model){
		
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
		
		return "mainpage/mainOverview";
	}
}
