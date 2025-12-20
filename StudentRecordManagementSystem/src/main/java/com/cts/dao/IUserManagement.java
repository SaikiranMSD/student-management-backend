package com.cts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.entity.User;

public interface IUserManagement extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.userName = :userName")
	User findByUserName(String userName);

}

