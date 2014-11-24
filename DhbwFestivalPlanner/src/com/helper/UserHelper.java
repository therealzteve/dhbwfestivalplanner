package com.helper;

import org.springframework.security.core.context.SecurityContextHolder;

import com.model.User;

public class UserHelper {

	public static User getCurrentUser(){
		User user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof User){
			user = (User) principal;
		}
		return user;
	}
}
