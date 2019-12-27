package com.test.elastic.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.test.elastic.domain.ErrorResponse;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception){
		
		System.out.println("======= Global Exception Handler ========");
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimestamp(System.currentTimeMillis());
		System.out.println("======= Global Exception Handler ========");
		return ResponseEntity.badRequest().body(errorResponse);
	}

}
