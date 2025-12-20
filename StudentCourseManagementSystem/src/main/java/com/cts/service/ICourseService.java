package com.cts.service;

import java.util.List;

import com.cts.entity.Course;

public interface ICourseService {

	public String addCourse(Course course);

	public String updateCourse(Course course);

	public String deleteCourse(Integer id);

	public Course getCourse(Integer id);

	public Integer getCourseId(String courseName);

	public List<Course> getAllCourses();

}
