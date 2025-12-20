package com.cts.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "studentmanagementsystem")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;
	@NotEmpty(message = "Please provide StudentName")
	private String studentName;
	@NotNull
	@Max(value = 30, message = "Age must be below 30")
	@Min(value = 15, message = "Age must be above 15")
	private Integer studentAge;
	@Email(message = "Please Provide Valid EmailAddress")
	private String studentEmail;
	@NotEmpty(message = "Please Provide Valid address")
	private String studentAddress;
	@NotEmpty(message = "Phone number must be 10 digits")
	@Size(max = 10)
	private String studentPhoneNumber;
	@NotEmpty(message = "firstCourseName must not be empty")
	private String firstCourseName;
	@NotEmpty(message = "secondCourseName must not be empty")
	private String secondCourseName;

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

	public Integer getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentPhoneNumber() {
		return studentPhoneNumber;
	}

	public void setStudentPhoneNumber(String studentPhoneNumber) {
		this.studentPhoneNumber = studentPhoneNumber;
	}

	public String getFirstCourseName() {
		return firstCourseName;
	}

	public void setFirstCourseName(String firstCourseName) {
		this.firstCourseName = firstCourseName;
	}

	public String getSecondCourseName() {
		return secondCourseName;
	}

	public void setSecondCourseName(String secondCourseName) {
		this.secondCourseName = secondCourseName;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentAge=" + studentAge
				+ ", studentEmail=" + studentEmail + ", studentAddress=" + studentAddress + ", studentPhoneNumber="
				+ studentPhoneNumber + ", firstCourseName=" + firstCourseName + ", secondCourseName=" + secondCourseName
				+ "]";
	}

//	@Transient
//	@OneToOne
//	public Enrollment enrollment;

}
