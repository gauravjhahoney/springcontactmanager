package com.SmartContact.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SmartContact.Entities.user;

public interface UserRepo extends JpaRepository<user, Integer>{
	
//	@Query("select u from user u where u.email= :email")
//public user getuserByUserName(@Param("email") String email);

}
