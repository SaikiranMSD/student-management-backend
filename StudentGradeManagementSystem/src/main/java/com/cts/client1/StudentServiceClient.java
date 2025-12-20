package com.cts.client1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.entity.Student;

@FeignClient(name = "StudentRecordManagement")
public interface StudentServiceClient {

	@GetMapping("/getdata/{id}")
	public ResponseEntity<Student> retriveStudentInfo(@PathVariable("id") Integer id);

}
