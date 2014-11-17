package com.event;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.component.Event;



@Controller
@RequestMapping("/")
public class EventController {

	@Autowired
	private SessionFactory sessionFactory;


	@RequestMapping("/helloWorld")
	public String hello(Model model) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
	    List<Event> events = session.createQuery("from Event").list();
		session.getTransaction().commit();

	
		for(Event ev : events){
			System.out.println(ev);
		}
		model.addAttribute("events", events);
		
		
		return "result";
	}
}