package com.example.SpringReddit.exception;

import lombok.Getter;

@Getter
public class CommentNotFoundException extends RuntimeException {

	public CommentNotFoundException(String id) {
		super("Comment not found with the id: " + id);
	}
}
