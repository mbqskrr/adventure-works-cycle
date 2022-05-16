package com.example.BalantaTaller1.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BalantaTaller1.model.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByUsername(String username);
}
