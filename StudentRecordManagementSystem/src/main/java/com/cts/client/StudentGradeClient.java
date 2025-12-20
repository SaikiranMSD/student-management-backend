package com.cts.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "GradeManagementService")
public interface StudentGradeClient {

	@DeleteMapping("/deletemarkssheet/{id}")
	public void delete(@PathVariable("id") Integer id);
	
	@PutMapping("/update/{id}/{name}")
	public void update(@PathVariable("id") Integer id, @PathVariable("name") String studentName) ;
}
