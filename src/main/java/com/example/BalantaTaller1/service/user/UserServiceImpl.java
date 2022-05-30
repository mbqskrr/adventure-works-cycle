package com.example.BalantaTaller1.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BalantaTaller1.model.user.UserAWC;
import com.example.BalantaTaller1.model.user.UserType;
import com.example.BalantaTaller1.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public void save(UserAWC user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void update(UserAWC user) {
		Optional<UserAWC> aux = userRepository.findById(user.getId());
		if(aux.isPresent()) {
			UserAWC u = aux.get();
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
			u.setType(user.getType());
			userRepository.save(u);
		}
	}

	public Iterable<UserAWC> findAll() {
		return userRepository.findAll();
	}
	
	public UserType[] getTypes() {
		return UserType.values();
	}
	
	public Optional<UserAWC> findById(Integer id) {
		return userRepository.findById(id);
		
	}

}
