package com.cts.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.client.StudentGradeClient;
import com.cts.dao.IStudentRecordManagement;
import com.cts.entity.Student;
import com.cts.studentNotFoundException.StudentNotFoundException;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private IStudentRecordManagement dao;

	@Autowired
	private StudentGradeClient client;

	@Override
	public Student getStudentData(Integer id) {
		logger.info("Getting student data for ID: {}", id);
		Optional<Student> studData = dao.findById(id);
		if (studData.isPresent()) {
			logger.info("Retrieved student data: {}", studData.get());
			return studData.get();
		} else {
			throw new StudentNotFoundException("Record Not Found With the given Id " + id);
		}
	}

	@Override
	public String postStudentData(Student student) {
		logger.info("Registering new student: {}", student);
		if (student != null) {
			Object sobj = dao.save(student);
			logger.info("New student registered: {}", student.getStudentId());
			return "Successfully Registered with id " + student.getStudentId();
		} else {
			throw new StudentNotFoundException("Please provide all details");
		}
	}

	@Override
	public String updateStudentData(Student student) {
		logger.info("Updating student data: {}", student);
		Optional<Student> studData = dao.findById(student.getStudentId());
		if (studData.isPresent()) {
			Student sobj = dao.save(student);
			client.update(student.getStudentId(), student.getStudentName());
			logger.info("Student data updated: {}", sobj.getStudentId());
			return "Record Updated Successfully with id " + sobj.getStudentId();
		} else {
			throw new StudentNotFoundException("Record Not Found With the given Id " + student.getStudentId());
		}
	}

	@Override
	public String deleteStudentData(Integer id) {
		logger.info("Deleting student with ID: {}", id);
		Optional<Student> studData = dao.findById(id);
		if (studData.isPresent()) {
			dao.deleteById(id);
			client.delete(id);
			logger.info("Student deleted: {}", id);
			return "Record Deleted Successfully with id " + id;
		} else {
			throw new StudentNotFoundException("Record Not Found With the given Id " + id);
		}
	}

	@Override
	public List<Student> getAllStudentData() {
		logger.info("Getting all student data");
		List<Student> getAllStudentData = dao.findAll();
		logger.info("Retrieved all student data: {}", getAllStudentData);
		return getAllStudentData;
	}
}
