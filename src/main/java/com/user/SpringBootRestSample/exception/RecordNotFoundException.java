package com.user.SpringBootRestSample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception{

	private static final long serialVersionUID = -4532671123340081743L;

	public RecordNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
