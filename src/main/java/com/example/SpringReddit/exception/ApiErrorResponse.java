package com.example.SpringReddit.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiErrorResponse {
	private final String exception;
	private final String message;
	private List<ObjectError> errors;
	private Map<String, String> missingParam;
	private String missingPathVariable;
	private String[] supportedMethods;
}
