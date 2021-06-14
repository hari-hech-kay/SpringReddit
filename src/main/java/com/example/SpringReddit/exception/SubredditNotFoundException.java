package com.example.SpringReddit.exception;

import lombok.Getter;

@Getter
public class SubredditNotFoundException extends RuntimeException {

	public SubredditNotFoundException(Long id) {
		super("Subreddit not found with the id: " + id);
	}

	public SubredditNotFoundException(String name) {
		super("Subreddit not found with the name: r/" + name);
	}
}
