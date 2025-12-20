package com.cts.service;

import java.util.List;

import com.cts.entity.Enrollment;

public interface IStudentGradeService {

	public String postMarks(Enrollment enroll);

	public Enrollment getMarksSheet(Integer id);

	public void delete(Integer id);

	public void update(String studentName, Integer studentId);
	
	public List<Enrollment> getAllStudentMarksSheet();

}
