package com.example.SpringReddit.exception;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class RedditExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {BadCredentialsException.class, DisabledException.class})
	public ResponseEntity<ApiErrorResponse> badCredentialsException(AuthenticationException exception) {
		System.out.println(exception.getMessage() + exception.getClass().getName());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ApiErrorResponse.builder()
						.exception(exception.getClass().getName())
						.message(exception.getMessage()).build());
	}

	@ExceptionHandler(value = {JWTCreationException.class, JWTVerificationException.class})
	public ResponseEntity<ApiErrorResponse> jwtException(RuntimeException exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ApiErrorResponse.builder()
						.exception(exception.getClass().getName())
						.message(exception.getMessage())
						.build());
	}

	@ExceptionHandler(value = {PostNotFoundException.class, SubredditNotFoundException.class, CommentNotFoundException.class, UserNotFoundException.class})
	public ResponseEntity<ApiErrorResponse> notFoundException(RuntimeException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ApiErrorResponse.builder()
						.exception(exception.getClass().getName())
						.message(exception.getMessage())
						.build());
	}

	@ExceptionHandler(value = RedditException.class)
	public ResponseEntity<ApiErrorResponse> redditException(RedditException exception) {
		return ResponseEntity.status(exception.getStatus())
				.body(ApiErrorResponse.builder()
						.exception(exception.getClass().getName())
						.message(exception.getMessage())
						.build());
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(status)
				.body(ApiErrorResponse.builder()
						.exception(exception.getClass().getName())
						.message(exception.getMessage())
						.supportedMethods(exception.getSupportedMethods())
						.build());
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(status)
				.body(ApiErrorResponse.builder()
						.exception(exception.getClass().getName())
						.message(exception.getMessage())
						.missingPathVariable(exception.getVariableName())
						.build());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(status)
				.body(ApiErrorResponse.builder()
						.exception(exception.getClass().getName())
						.message(exception.getMessage())
						.missingParam(Map.of("name", exception.getParameterName(), "type", exception.getParameterType()))
						.build());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(status)
				.body(ApiErrorResponse.builder()
						.exception(exception.getClass().getName())
						.message(exception.getMessage())
						.errors(exception.getAllErrors())
						.build());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.status(status)
				.body(ApiErrorResponse.builder()
						.exception(exception.getClass().getName())
						.message(exception.getMessage())
						.build());
	}
}
