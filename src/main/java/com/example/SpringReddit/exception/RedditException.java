package com.example.SpringReddit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class RedditException extends RuntimeException {
	private final HttpStatus status;
	private final String message;
	private Exception exception;

	public RedditException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}
