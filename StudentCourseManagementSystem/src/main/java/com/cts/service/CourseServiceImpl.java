package com.cts.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.courseNotFoundException.CourseNotFoundException;
import com.cts.dao.IStudentCourseManagement;
import com.cts.entity.Course;

@Service
@Transactional
public class CourseServiceImpl implements ICourseService {

	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	@Autowired
	private IStudentCourseManagement dao;

	@Override
	public String addCourse(Course course) {
		if (course != null) {
			Course addCourse = dao.save(course);
			logger.info("Successfully added the course with course id: {}", course.getCourseId());
			return "Successfully added the course with course id " + course.getCourseId();
		} else {
			logger.error("Please provide all Course details");
			throw new CourseNotFoundException("Please provide all Course details");
		}
	}

	@Override
	public String updateCourse(Course course) {
		Optional<Course> studData = dao.findById(course.getCourseId());
		if (studData.isPresent()) {
			Course sobj = dao.save(course);
			logger.info("Successfully updated the course: {}", sobj.getCourseId());
			return "Successfully updated the course " + sobj.getCourseId();
		} else {
			logger.error("Course Not Found with given courseId {}", course.getCourseId());
			throw new CourseNotFoundException("Course Not Found with given courseId " + course.getCourseId());
		}
	}

	@Override
	public String deleteCourse(Integer id) {
		Optional<Course> studData = dao.findById(id);
		if (studData.isPresent()) {
			dao.deleteById(id);
			logger.info("Record Deleted Successfully with id: {}", id);
			return "Record Deleted Successfully with id " + id;
		} else {
			logger.error("Course Not Found With the given Id {}", id);
			throw new CourseNotFoundException("Course Not Found With the given Id " + id);
		}
	}

	@Override
	public Course getCourse(Integer id) {
		Optional<Course> studData = dao.findById(id);
		if (studData.isPresent())
			return studData.get();
		else {
			logger.error("Course Not Found With the given Id {}", id);
			throw new CourseNotFoundException("Course Not Found With the given Id " + id);
		}
	}

	@Override
	public Integer getCourseId(String courseName) {
		Integer courseId = dao.findByCourseName(courseName);
		if (courseId != null)
			return courseId;
		else {
			logger.error("Course Not Found With the given courseName {}", courseName);
			throw new CourseNotFoundException("Course Not Found With the given courseName " + courseName);
		}
	}

	@Override
	public List<Course> getAllCourses() {
		logger.info("Getting all courses");
		List<Course> allCourses = dao.findAll();
		logger.info("Retrieved all courses: {}", allCourses);
		return allCourses;
	}
}
