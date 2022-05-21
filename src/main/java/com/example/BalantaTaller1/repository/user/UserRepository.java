package com.example.BalantaTaller1.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BalantaTaller1.model.user.UserAWC;

public interface UserRepository extends JpaRepository<UserAWC, Integer>{
	
	public UserAWC findByUsername(String username);
}
