package com.messages;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.event.EventUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helper.UserHelper;
import com.model.Event;
import com.model.Guest;
import com.model.User;

@Controller
@RequestMapping("/message")
public class MessagesController {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private MailSender mailSender;

	@RequestMapping("/send")
	public String send(
			Model model,
			@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "message", required = false, defaultValue = "") String message) {
		Event event = getEvent(id, UserHelper.getCurrentUser());
		if (event != null) {
			sendMessage(event, message);
			return "message/success";
		}
		return "message/error";
	}

	@RequestMapping("/create")
	public String create() {
		return "message/create";
	}

	private void sendMessage(Event event, String message) {
		SimpleMailMessage mail = new SimpleMailMessage();
		List<String> recipients = getMessageRecipients(event);
		mail.setTo((String[]) recipients.toArray());
		mail.setText(createMessageText(message, event));
		mail.setFrom("noreply@dhbwfestivalplanner.de");
		mailSender.send(mail);
	}

	private List<String> getMessageRecipients(Event event) {
		List<String> recipients = new ArrayList<String>();

		for (Guest g : event.getGuests()) {
			addRecipient(recipients, g);
		}
		return recipients;
	}

	private void addRecipient(List<String> recipients, Guest g) {
		if (g.isReceivesEmail()) {
			recipients.add(g.getEmail());
		}
	}

	private Event getEvent(int id, User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Event event = (Event) session.get(Event.class, id);
		session.getTransaction().commit();
		session.close();
		if (event.getCreator().getId() == user.getId()) {
			return event;
		}
		return null;
	}

	private String createMessageText(String userMessage, Event event) {
		String message = "Sie haben eine Nachricht von: "
				+ event.getCreator().getName()
				+ " zum Event: "
				+ event.getName()
				+ "\n \n "
				+ userMessage
				+ "\n Bitte antworten Sie nicht direkt auf diese Email."
				+ " Den Veranstalter können Sie unter dieser Adresse erreichen: "
				+ event.getCreator().getEmail();

		return message;
	}
}
