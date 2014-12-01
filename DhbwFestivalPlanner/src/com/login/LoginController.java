package com.login;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.helper.RandomString;
import com.model.User; 

@Controller
public class LoginController { 

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	protected AuthenticationManager authenticationManager;

	@Autowired
	private MailSender mailSender;

	/**
	 * Handles the login form but only GET. POST is managed by Spring Security
	 * 
	 * @param model
	 * @param error
	 * @param logout
	 * @return
	 */
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login(Model model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		// If we got an error we display a message to the user
		if (error != null) {
			model.addAttribute("error", true);
		}

		// Show logout message to the user
		if (logout != null) {
			model.addAttribute("logout", true);
		}

		// Display the login page
		return "login/index";

	}

	/**
	 * Handles registration post request, adds user to database etc.
	 *
	 * @param model
	 * @param username
	 * @param password
	 * @param email
	 * @param name
	 * @param regSuccess
	 * @return
	 */

	//regSuccess funktioniert leider noch nicht
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(
			Model model,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "name", required = false, defaultValue = "") String name)
			{

		// TODO: Fill validation later
		validate();

		// Encrypt password for database
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		// Create unlock code:
		RandomString rs = new RandomString(40);

		// Create user model
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(hashedPassword);
		user.setName(name);
		user.setCode(rs.nextString());
		user.setBirth(new Date());
		user.setIsEnabled(false);

		// Save user
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();

		// Send unlock E-Mail
		try {
			sendUnlockEmail(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("regSuccess", true);

		// Redirect to main page
		return "login/index";
	}

	/**
	 * Handles unlock request from user's E-Mail link
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/unlock", method = RequestMethod.GET)
	public String unlock(HttpServletRequest request, Model model,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "code", required = true) String code) {

		// Get user based on his username for security reasons
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(User.class);
		c.add(Restrictions.eq("username", username));
		List<User> userList = c.list();

		// Check unlock code and enable user
		if (userList.size() > 0) {
			User u = userList.get(0);
			if (u.getCode().equals(code)) {
				u.setCode("");
				u.setIsEnabled(true);
				session.save(u);
				session.getTransaction().commit();
			} else {
				// Add handling when user is not registered
				return "login/error";
			}
		}
		session.close();

		// True to display unlock message to user
		model.addAttribute("unlock", true);

		// Redirect to main page
		return "redirect:login";
	}

	/**
	 * Sends an E-Mail to client with unlock code
	 *
	 * @param u
	 */
	protected void sendUnlockEmail(User u) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("info@dhbwfestivalplanner.de");
		message.setTo(u.getEmail());
		message.setSubject("Deine Registrierung bei DhbwFestivalplanner");
		message.setText("Vielen Dank f&uumlr deine Registrierung bei DhbwFestivalplanner!\n"
				+ "Um deine Registrierung abzuschliessen musst du folgenden Link aufrufen:\n"
				+ "http://dhbwfestivalpanner/unlock?username="+u.getUsername()+"&code="+u.getCode());
		mailSender.send(message);
	}

	public void validate() {

	}
}
