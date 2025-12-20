package com.cts.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Enrollment {

	@Id
	@NotNull(message = "Please provide valid Student Id")
	private Integer studentId;
	private String studentName;
	@Max(value = 100, message = "Max Marks should not be greater than 100")
	@Min(value = 0, message = "Marks must be a positive value")
	@NotNull
	private Integer marksInSubject1;
	@Max(value = 100, message = "Max Marks should not be greater than 100")
	@Min(value = 0, message = "Marks must be a positive value")
	@NotNull
	private Integer marksInSubject2;
	@NotNull
	private Integer course1Id;
	@NotNull
	private Integer course2Id;
	@NotNull
	private String courseName1;
	@NotNull
	private String courseName2;
	private String status;
	private String grade;
	private String percentage;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getMarksInSubject1() {
		return marksInSubject1;
	}

	public void setMarksInSubject1(Integer marksInSubject1) {
		this.marksInSubject1 = marksInSubject1;
	}

	public Integer getMarksInSubject2() {
		return marksInSubject2;
	}

	public void setMarksInSubject2(Integer marksInSubject2) {
		this.marksInSubject2 = marksInSubject2;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public Integer getCourse1Id() {
		return course1Id;
	}

	public void setCourse1Id(Integer course1Id) {
		this.course1Id = course1Id;
	}

	public Integer getCourse2Id() {
		return course2Id;
	}

	public void setCourse2Id(Integer course2Id) {
		this.course2Id = course2Id;
	}

	public String getCourseName1() {
		return courseName1;
	}

	public void setCourseName1(String courseName1) {
		this.courseName1 = courseName1;
	}

	public String getCourseName2() {
		return courseName2;
	}

	public void setCourseName2(String courseName2) {
		this.courseName2 = courseName2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Enrollment [studentId=" + studentId + ", studentName=" + studentName + ", marksInSubject1="
				+ marksInSubject1 + ", marksInSubject2=" + marksInSubject2 + ", grade=" + grade + ", percentage="
				+ percentage + ", course1Id=" + course1Id + ", course2Id=" + course2Id + ", courseName1=" + courseName1
				+ ", courseName2=" + courseName2 + ", status=" + status + "]";
	}

}
