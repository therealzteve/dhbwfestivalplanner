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

import com.model.Event;



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

	
		for(Event ev : events){
			System.out.println(ev);
		}
		model.addAttribute("events", events);
		
		
		return "event/result";
	}
	
	@RequestMapping("/create")
	public String create(Model model) {
		return "event/create";
	}
	
	@RequestMapping("/save")
	public String save(Model model) {

		Event newEvent = new Event();
		
		newEvent.setName("the new event");
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
	    session.save(newEvent);
		session.getTransaction().commit();

	
		
		model.addAttribute("event", newEvent);
		
		
		return "event";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
	    List<Event> events = session.createQuery("from Event").list();
		session.getTransaction().commit();

	
		for(Event ev : events){
			System.out.println(ev);
		}
		model.addAttribute("events", events);
		
		
		return "event/list";
	}
	
	@RequestMapping("/edit")
	public String edit(Model model) {
		
		
		
		return "event/edit";
	}
	
	@RequestMapping("/display")
	public String display(Model model) {
		
		
		
		return "event/display";
	}
	
}