package com.model;


public class Message {
	String content;
	
	User veranstalter;
	Message vorgaenger;
	Guest guest;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getVeranstalter() {
		return veranstalter;
	}
	public void setVeranstalter(User veranstalter) {
		this.veranstalter = veranstalter;
	}
	public Message getVorgaenger() {
		return vorgaenger;
	}
	public void setVorgaenger(Message vorgaenger) {
		this.vorgaenger = vorgaenger;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
}
