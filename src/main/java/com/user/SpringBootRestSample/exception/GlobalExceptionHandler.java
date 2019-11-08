package com.user.SpringBootRestSample.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> recordNotFoundException(RecordNotFoundException recordNotFoundException, 
			WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(), recordNotFoundException.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception exception, WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
