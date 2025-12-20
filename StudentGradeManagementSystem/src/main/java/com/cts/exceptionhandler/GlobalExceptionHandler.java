package com.cts.exceptionhandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.IdNotFoundException.IdNotFoundException;
import com.cts.model.ErrorDetails;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ IdNotFoundException.class })
	public ResponseEntity<ErrorDetails> showMSg(IdNotFoundException s) {
		System.out.println("GlobalExceptionHandler.showMSg()");

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), s.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
		Map<String, String> map = new HashMap<>();
		ex.getConstraintViolations().forEach(violation -> {
			String fieldName = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			map.put(fieldName, message);
		});
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
}
