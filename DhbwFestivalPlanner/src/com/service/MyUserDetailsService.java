package com.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 

 
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
 
	@Autowired
	private SessionFactory sessionFactory;
 
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) 
		throws UsernameNotFoundException {
 
		com.model.User user = getUser(username);
		return user;
 
	}
 
//	// Converts commodel.User user to
//	// org.springframework.security.core.userdetails.User
//	// Currently auto enabled, maybe make enabling process later
//	private User buildUserForAuthentication(com.model.User user, 
//		List<GrantedAuthority> authorities) {
//		User u = new User(user.getUsername(), user.getPassword(), 
//				true, true, true, true, authorities);
//		return u;
//	}
// 
//	//Currently just ROLE_USER
//	private List<GrantedAuthority> buildUserAuthority() {
// 
//		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
// 
//		setAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
// 
//		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
// 
//		return result;
//	}
	
	private com.model.User getUser(String username){
		List<com.model.User> users = new ArrayList<com.model.User>();
		 
		users = sessionFactory.getCurrentSession()
			.createQuery("from User where username=?")
			.setParameter(0, username)
			.list();
 
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
 
}