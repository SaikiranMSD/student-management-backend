package com.cts.service;

import java.util.List;

import com.cts.entity.Student;

public interface IStudentService {

	public Student getStudentData(Integer id);

	public String postStudentData(Student student);

	public String updateStudentData(Student student);
	
	public String deleteStudentData(Integer id);
	
	public List<Student> getAllStudentData();

	public Student getStudentByName(String studentName);

}
