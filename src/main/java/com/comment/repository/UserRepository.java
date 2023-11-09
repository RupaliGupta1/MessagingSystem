package com.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comment.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByName(String commentFrom); 

}
