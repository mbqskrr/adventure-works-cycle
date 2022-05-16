package com.example.BalantaTaller1.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BalantaTaller1.model.user.User;
import com.example.BalantaTaller1.model.user.UserType;
import com.example.BalantaTaller1.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void update(User user) {
		Optional<User> aux = userRepository.findById(user.getId());
		if(aux.isPresent()) {
			User u = aux.get();
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
			u.setType(user.getType());
			userRepository.save(u);
		}
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}
	
	public UserType[] getTypes() {
		return UserType.values();
	}
	
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
		
	}

}
