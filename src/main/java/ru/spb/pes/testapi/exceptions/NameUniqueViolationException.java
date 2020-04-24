package ru.spb.pes.testapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class NameUniqueViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public NameUniqueViolationException(String message) {
		super(message);
	}
	
}
