package com.example.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class Authenticationservice {
	
	public boolean authenticate(String username, String password) {
			
			boolean isValidUserName = username.equalsIgnoreCase("coursera");
			boolean isValidPassword = password.equalsIgnoreCase("123");
			
			return isValidUserName && isValidPassword;
		}

}
