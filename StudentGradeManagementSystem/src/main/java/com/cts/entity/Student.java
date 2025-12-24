package com.cts.entity;

import lombok.Data;

/**
 * Student entity - Copy for Grade Service independent build
 * Original in StudentRecordManagementSystem
 */
@Data
public class Student {
    private Integer studentId;
    private String studentName;
    private Integer studentAge;
    private String studentEmail;
    private String studentAddress;
    private String studentPhoneNumber;
    private String firstCourseName;
    private String secondCourseName;
}

