package com.example.SpringReddit.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiErrorResponse {
	//private final HttpStatus status;
	private final String exception;
	private final String message;
	private final String explanation;

	public ApiErrorResponse(String explanation, String exception, String message) {
		this.explanation = explanation;
		this.exception = exception;
		this.message = message;
	}
}
