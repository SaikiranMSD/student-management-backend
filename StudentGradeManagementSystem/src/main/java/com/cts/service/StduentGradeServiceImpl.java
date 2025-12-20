package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.IdNotFoundException.IdNotFoundException;
import com.cts.dao.IStudentGradeDao;
import com.cts.dao.IStudentRecordManagement;
import com.cts.entity.Enrollment;
import com.cts.entity.Student;

@Service
@Transactional
public class StduentGradeServiceImpl implements IStudentGradeService {

	private static final Logger logger = LoggerFactory.getLogger(StduentGradeServiceImpl.class);

	@Autowired
	private IStudentGradeDao dao;

	@Autowired
	private IStudentRecordManagement dao1;

	@Override
	public String postMarks(Enrollment enroll) {
		logger.info("Posting marks for student ID: {}", enroll.getStudentId());
		Optional<Student> stud = dao1.findById(enroll.getStudentId());
		if (stud.isPresent()) {
			Integer marks1 = enroll.getMarksInSubject1();
			Integer marks2 = enroll.getMarksInSubject2();
			Integer marks = marks1 + marks2;
			double totalMarks = 200;
			double percentage = ((marks1 + marks2) / (double) totalMarks) * 100;
			enroll.setPercentage(percentage + "%");
			enroll.setStatus((marks >= 100) ? "PASS" : "FAIL");
			if (marks >= 180) {
				enroll.setGrade("A+");
			} else if (marks >= 160) {
				enroll.setGrade("A");
			} else if (marks >= 140) {
				enroll.setGrade("B+");
			} else if (marks >= 120) {
				enroll.setGrade("B");
			} else if (marks >= 100) {
				enroll.setGrade("C");
			} else {
				enroll.setGrade("F");
			}
			dao.save(enroll);
			logger.info("Marks posted for student ID: {}", enroll.getStudentId());
			logger.debug("Marks data: {}", enroll);
			return "Grade Calculated for the StudentId: " + enroll.getStudentId();
		} else {
			logger.error("Student not found with ID: {}", enroll.getStudentId());
			return "Student Not Found";
		}
	}

	@Override
	public Enrollment getMarksSheet(Integer id) {
		logger.info("Getting marks sheet for student ID: {}", id);
		Optional<Student> stud = dao1.findById(id);
		if (stud.isPresent()) {
			Enrollment enrollment = null;
			Optional<Enrollment> enroll = dao.findById(id);
			if (enroll.isPresent()) {
				enrollment = enroll.get();
			} else {
				throw new IdNotFoundException("MarksSheet Not Found With the given Id " + id);
			}
			logger.debug("Marks sheet: {}", enrollment);
			return enrollment;
		} else {
			logger.error("Student not found with ID: {}", id);
			throw new IdNotFoundException("MarksSheet Not Found With the given Id " + id);
		}
	}

	@Override
	public void delete(Integer id) {
		logger.info("Deleting marks sheet for student ID: {}", id);
		Optional<Enrollment> deleteById = dao.findById(id);
		if (deleteById.isPresent()) {
			dao.deleteById(id);
			logger.info("Marks sheet deleted for student ID: {}", id);
		}
	}

	@Override
	public void update(String studentName, Integer studentId) {
		logger.info("Updating student name for student ID: {}", studentId);
		Optional<Enrollment> enroll = dao.findById(studentId);
		if (enroll.isPresent()) {
			Enrollment enrollment = enroll.get();
			enrollment.setStudentName(studentName);
			enrollment.setStudentId(studentId);
			dao.save(enrollment);
			logger.info("Student name updated for student ID: {}", studentId);
		} else {
			logger.error("Marks sheet not found with ID: {}", studentId);
		}
	}

	@Override
	public List<Enrollment> getAllStudentMarksSheet() {
		logger.info("Getting all student marks sheets");
		List<Enrollment> findAll = dao.findAll();
		logger.debug("All student marks sheets: {}", findAll);
		return findAll;
	}
}
