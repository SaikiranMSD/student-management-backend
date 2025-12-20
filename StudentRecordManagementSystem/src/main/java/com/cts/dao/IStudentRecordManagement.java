package com.cts.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.Student;

public interface IStudentRecordManagement extends JpaRepository<Student, Integer> {

}
