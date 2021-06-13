package com.example.SpringReddit.exception;

public class CommentNotFoundException extends RuntimeException {

	public CommentNotFoundException(String message) {
		super(message);
	}
}
