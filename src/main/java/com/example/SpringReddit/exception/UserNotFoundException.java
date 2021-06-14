package com.example.SpringReddit.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String username) {
		super("User not found with the username: u/" + username);
	}
}
