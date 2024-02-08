package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFound extends RuntimeException {
	public PersonNotFound(String id) {
		super("Person not found with id " + id);
	}
}
