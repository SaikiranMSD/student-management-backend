package com.cts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.entity.Course;

public interface IStudentCourseManagement extends JpaRepository<Course, Integer> {
	
	@Query("SELECT courseId FROM Course WHERE courseName=:courseName")
     Integer findByCourseName(String courseName);

}
