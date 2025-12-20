package com.cts.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.entity.Enrollment;

@Repository
public interface IStudentGradeDao extends JpaRepository<Enrollment, Integer> {
	
//	 @Query("SELECT e FROM Enrollment e WHERE e.studentId= :id")
//	Enrollment findByStudentId(Integer id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Enrollment e WHERE e.studentId NOT IN :ids")
	void deleteIdNotFoundInStudentId(List<Integer> ids);

}
