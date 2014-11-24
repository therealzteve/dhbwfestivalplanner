package com.login;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.model.User;

@Controller
public class LoginController {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	protected AuthenticationManager authenticationManager;

	/**
	 *  Handles the login form but only GET. POST is managed by Spring
	 *  Security
	 * 
	 * @param model
	 * @param error
	 * @param logout
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		
		//If we got an error we display a message to the user
		if (error != null) {
			model.addAttribute("error", true);
		}

		//Show logout message to the user
		if (logout != null) {
			model.addAttribute("logout", true);
		}

		//Display the login page
		return "login/loginForm";

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, Model model,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "email", required = true) String email) {

		
		//TODO: Fill validation later
		validate();
		
		//Encrypt password for database
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		//Create user model
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(hashedPassword);
		user.setName("Temporary Test Name");
		//Save user
		saveUser(user);

		// Auto Login:
		autologin(username, password, request);

		//Redirect to main page
		return "redirect:event/list";
	}

	/**
	 * Show registration form
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistration(Model model) {
		return "login/registerForm";
	}

	
	protected void autologin(String username, String password, HttpServletRequest request){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);

		// generate session if one doesn't exist
		request.getSession();

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager
				.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

	}
	
	
	/**
	 *  Saves user in database
	 * @param user
	 */
	protected void saveUser(User user){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}
	
	public void validate() {

	}
}
