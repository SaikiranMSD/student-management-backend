package com.cts.exceptionhandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.validation.UnexpectedTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.courseNotFoundException.CourseNotFoundException;
import com.cts.model.ErrorDetails;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ CourseNotFoundException.class })
	public ResponseEntity<ErrorDetails> showMSg(CourseNotFoundException s) {
		System.out.println("GlobalExceptionHandler.showMSg()");

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), s.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> validationMethod(MethodArgumentNotValidException ex) {
		Map<String, String> map = new HashMap();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			map.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
	}

}
