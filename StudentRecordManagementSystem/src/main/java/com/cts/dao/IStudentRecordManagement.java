package com.cts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.entity.Student;

public interface IStudentRecordManagement extends JpaRepository<Student, Integer> {

	@Query("SELECT s FROM Student s WHERE s.studentName = :studentName")
	Student findByStudentName(String studentName);

}
