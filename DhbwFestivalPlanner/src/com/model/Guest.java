package com.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "Guests")
public class Guest {

	@Id
	int id;

	String name, email;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "Event_ID")
	Event event;

	boolean receivesEmail, confirmed, receivedInvitation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public boolean isReceivesEmail() {
		return receivesEmail;
	}

	public void setReceivesEmail(boolean receivesEmail) {
		this.receivesEmail = receivesEmail;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	/**
	 * Compares 2 guests without the id
	 * @param g
	 * @return
	 */
	public boolean softEquals(Guest g){
		if(event.equals(g.getEvent())
				&&  name.equals(g.getName())
				&& email.equals(g.getEmail())
				){
			return true;
		}
		return false;
	}

	public boolean isReceivedInvitation() {
		return receivedInvitation;
	}

	public void setReceivedInvitation(boolean receivedInvitation) {
		this.receivedInvitation = receivedInvitation;
	}
}
