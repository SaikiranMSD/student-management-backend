package com.cts.client2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "StudentCourseManagement")
public interface StudentCourseClient {

	@GetMapping("/getid/{courseName}")
	public Integer getCourseIdByName(@PathVariable("courseName") String courseName);

}
