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
import org.springframework.web.bind.annotation.RestController;

import com.cts.client1.StudentServiceClient;
import com.cts.client2.StudentCourseClient;
import com.cts.entity.Enrollment;
import com.cts.service.StduentGradeServiceImpl;

@RestController
public class RequestHandler {

	private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

	@Autowired
	private StudentServiceClient client1;

	@Autowired
	private StduentGradeServiceImpl service;

	@Autowired
	private StudentCourseClient client2;

	@PostMapping("/postmarksdata/{id}/{marks1}/{marks2}")
	public ResponseEntity<String> postMarks(@Valid @PathVariable("id") Integer id,
			@PathVariable("marks1") Integer marks1, @PathVariable("marks2") Integer marks2) {
		logger.info("Posting marks data for student ID: {}", id);
		Enrollment e = new Enrollment();
		String postMarks =null;
		e.setMarksInSubject1(marks1);
		e.setMarksInSubject2(marks2);
		e.setStudentId(id);
		if (client1.retriveStudentInfo(id).getBody().getStudentId() != null)
		{String studName = client1.retriveStudentInfo(id).getBody().getStudentName();

		e.setStudentName(studName);
		String firstcourseName = client1.retriveStudentInfo(id).getBody().getFirstCourseName();
		String secondcourseName = client1.retriveStudentInfo(id).getBody().getSecondCourseName();
		Integer course1 = client2.getCourseIdByName(firstcourseName);
		Integer course2 = client2.getCourseIdByName(secondcourseName);
		e.setCourse1Id(course1);
		e.setCourse2Id(course2);
		e.setCourseName1(firstcourseName);
		e.setCourseName2(secondcourseName);
		postMarks =service.postMarks(e);
		logger.info("Marks data posted for student ID: {}", id);
		logger.debug("Marks data: {}", e);
		logger.debug("Response: {}", postMarks);
	}
		return new ResponseEntity<String>(postMarks, HttpStatus.OK);
	}

	@GetMapping("/getmarkssheet/{id}")
	public ResponseEntity<Enrollment> getMarksSheet(@PathVariable Integer id) {
		logger.info("Getting marks sheet for student ID: {}", id);
		Enrollment marksSheet = service.getMarksSheet(id);
		logger.debug("Marks sheet: {}", marksSheet);
		return new ResponseEntity<Enrollment>(marksSheet, HttpStatus.OK);
	}

	@DeleteMapping("/deletemarkssheet/{id}")
	public void delete(@PathVariable("id") Integer id) {
		logger.info("Deleting marks sheet for student ID: {}", id);
		service.delete(id);
		logger.info("Marks sheet deleted for student ID: {}", id);
	}

	@PutMapping("/update/{id}/{name}")
	public void update(@PathVariable("id") Integer id, @PathVariable("name") String studentName) {
		logger.info("Updating student name for student ID: {}", id);
		service.update(studentName, id);
		logger.info("Student name updated for student ID: {}", id);
	}

	@GetMapping("/getAllStudentMarks")
	public ResponseEntity<List<Enrollment>> getAllStudentMarksSheet() {
		logger.info("Getting all student marks sheets");
		List<Enrollment> allStudentMarksSheet = service.getAllStudentMarksSheet();
		logger.debug("All student marks sheets: {}", allStudentMarksSheet);
		return new ResponseEntity<List<Enrollment>>(allStudentMarksSheet, HttpStatus.OK);
	}
}
