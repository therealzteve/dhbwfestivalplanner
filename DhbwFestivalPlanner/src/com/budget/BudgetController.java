package com.budget;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.factory.EventFactory;
import com.helper.UserHelper;
import com.model.BudgetPosition;
import com.model.Event;

@Controller
@RequestMapping("/budget")
public class BudgetController {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	EventFactory eventFactory;

	@RequestMapping("/edit")
	public void edit(Model model, @RequestBody BudgetPosition budgetPosition) {

		if (hasPermission(budgetPosition)) {
			save(budgetPosition);
		}
	}

	@RequestMapping("/add")
	public void add(Model model, @RequestParam("eventId") int eventId,
			@RequestParam("amount") int amount,
			@RequestParam("price") float price,
			@RequestParam("name") String name) {

		Event event = eventFactory.getEvent(eventId, false);

		BudgetPosition budgetPosition = buildBudgetPosition(amount, price, name);

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.refresh(event.getBudget());
		Hibernate.initialize(event.getBudget());
		event.getBudget().add(budgetPosition);
		session.saveOrUpdate(budgetPosition);
		session.getTransaction().commit();
		session.close();
		
	}

	private void save(BudgetPosition budgetPosition) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(budgetPosition);
		session.getTransaction().commit();
		session.close();
	}
	

	private boolean hasPermission(BudgetPosition budgetPosition) {
		if (budgetPosition.getUser().getId() == UserHelper.getCurrentUser()
				.getId()) {
			return true;
		}
		return false;
	}

	private BudgetPosition buildBudgetPosition(int amount, float price,
			String name) {
		BudgetPosition budgetPosition = new BudgetPosition();

		budgetPosition.setAmount(amount);
		budgetPosition.setPrice(price);
		budgetPosition.setName(name);

		return budgetPosition;
	}
}
