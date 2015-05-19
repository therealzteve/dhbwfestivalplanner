package com.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "budget")
public class Budget {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id = 0;

	@OneToOne
	private Event event;
	
	@OneToMany
	private List<BudgetPosition> budgetPositions;

	public float getTotal() {
		float total = 0;

		for (BudgetPosition budgetPosition : budgetPositions) {
			total += (budgetPosition.getPrice() * budgetPosition.getAmount());
		}

		return total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<BudgetPosition> getBudgetPositions() {
		return budgetPositions;
	}

	public void setBudgetPositions(List<BudgetPosition> budgetPositions) {
		this.budgetPositions = budgetPositions;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return event.getCreator();
	}
}
