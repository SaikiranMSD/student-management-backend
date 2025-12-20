package com.cts.service;

import com.cts.entity.Course;

public interface ICourseService {

	public String addCourse(Course course);

	public String updateCourse(Course course);

	public String deleteCourse(Integer id);

	public Course getCourse(Integer id);

	public Integer getCourseId(String courseName);

}
