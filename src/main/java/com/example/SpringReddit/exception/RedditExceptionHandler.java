package com.example.SpringReddit.exception;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class RedditExceptionHandler {

	@ExceptionHandler(value = {BadCredentialsException.class, DisabledException.class})
	public ResponseEntity<ApiErrorResponse> badCredentialsException(AuthenticationException exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ApiErrorResponse.builder()
						.exception(exception.getClass().getName())
						.message(exception.getMessage())
						.explanation(exception.getExplanation()).build());
	}

	@ExceptionHandler(value = {JWTCreationException.class, JWTVerificationException.class})
	public ResponseEntity<ApiErrorResponse> jwtException(RuntimeException exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ApiErrorResponse.builder()
						.exception(exception.getClass().getName())
						.message(exception.getMessage())
						.explanation(exception.toString()).build());
	}

	@ExceptionHandler(value = {PostNotFoundException.class, SubredditNotFoundException.class, CommentNotFoundException.class})
	public ResponseEntity<ApiErrorResponse> notFoundException(RuntimeException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ApiErrorResponse.builder()
						.exception(exception.getClass().getName())
						.message(exception.getMessage())
						.explanation(exception.toString()).build());
	}

	@ExceptionHandler(value = RedditException.class)
	public ResponseEntity<ApiErrorResponse> redditException(RedditException exception) {
		return ResponseEntity.status(exception.getStatus())
				.body(ApiErrorResponse.builder()
						.exception(exception.getClass().getName())
						.message(exception.getMessage())
						.explanation(exception.toString()).build());
	}

}
