package com.example.SpringReddit.exception;

public class SubredditNotFoundException extends RuntimeException {

	public SubredditNotFoundException(String message) {
		super(message);
	}
}
