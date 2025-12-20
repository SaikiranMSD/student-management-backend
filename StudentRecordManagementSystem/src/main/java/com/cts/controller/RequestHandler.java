package com.cts.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.Student;
import com.cts.service.StudentServiceImpl;

@RestController
public class RequestHandler {

	private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

	@Autowired
	private StudentServiceImpl service;

	@GetMapping("/getdata/{id}")
	public ResponseEntity<Student> getStudentInfo(@PathVariable("id") Integer id) {
		logger.info("Getting student information for ID: {}", id);
		Student student = service.getStudentData(id);
		logger.info("Retrieved student information: {}", student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@PostMapping("/postdata")
	public ResponseEntity<String> postStudentData(@Valid @RequestBody Student student) {
		logger.info("Received request to create new student: {}", student);
		String message = service.postStudentData(student);
		logger.info("Response after creating new student: {}", message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping("/deletedata/{id}")
	public ResponseEntity<String> deleteStudentInfo(@PathVariable("id") Integer id) {
		logger.info("Deleting student with ID: {}", id);
		String message = service.deleteStudentData(id);
		logger.info("Response after deleting student: {}", message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PutMapping("/updatedata")
	public ResponseEntity<String> updateStudentData(@Valid @RequestBody Student student) {
		logger.info("Updating student information: {}", student);
		String message = service.updateStudentData(student);
		logger.info("Response after updating student: {}", message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/getalldata")
	public ResponseEntity<List<Student>> getAllStudentData() {
		logger.info("Getting all student data");
		List<Student> allStudentData = service.getAllStudentData();
		logger.info("Retrieved all student data: {}", allStudentData);
		return new ResponseEntity<>(allStudentData, HttpStatus.OK);
	}
}
