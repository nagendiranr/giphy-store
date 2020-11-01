package com.org.giphystore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.org.giphystore.controller.GiphyController;

@ControllerAdvice(assignableTypes = GiphyController.class)
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.error(ex.getMessage(), ex);
		return super.handleMissingPathVariable(ex, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error(ex.getMessage(), ex);
		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object>handleGenericException(Exception ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, null, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		body = ErrorResponse.builder().statusCode(status.value())
				.message(ex.getMessage())
				.build();
		logger.error(ex.getMessage(), ex);
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}	
}
