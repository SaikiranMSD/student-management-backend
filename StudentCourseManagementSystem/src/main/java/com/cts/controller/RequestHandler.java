package com.cts.controller;

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

import com.cts.entity.Course;
import com.cts.service.CourseServiceImpl;

@RestController
public class RequestHandler {

	private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

	@Autowired
	private CourseServiceImpl service;

	@GetMapping("/getdata/{id}")
	public ResponseEntity<Course> getCourseInfo(@PathVariable("id") Integer id) {
		logger.info("Fetching course information for id: {}", id);
		Course course = service.getCourse(id);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@PostMapping("/postdata")
	public ResponseEntity<String> postCourseData(@Valid @RequestBody Course student) {
		logger.info("Adding new course: {}", student);
		String message = service.addCourse(student);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping("/deletedata/{id}")
	public ResponseEntity<String> deleteCourseInfo(@PathVariable("id") Integer id) {
		logger.info("Deleting course with id: {}", id);
		String message = service.deleteCourse(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PutMapping("/updatedata")
	public ResponseEntity<String> updateCourseData(@Valid @RequestBody Course student) {
		logger.info("Updating course: {}", student);
		String message = service.updateCourse(student);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/getid/{courseName}")
	public Integer getCourseIdByName(@PathVariable("courseName") String courseName) {
		logger.info("Fetching course id for name: {}", courseName);
		Integer courseId = service.getCourseId(courseName);
		return courseId;
	}

}
