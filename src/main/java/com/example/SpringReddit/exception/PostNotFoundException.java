package com.example.SpringReddit.exception;

import lombok.Getter;

@Getter
public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException(String id) {
		super("Post not found with the id: " + id);
	}
}
