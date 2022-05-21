package com.example.BalantaTaller1.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.BalantaTaller1.model.user.UserAWC;
import com.example.BalantaTaller1.repository.user.UserRepository;

@Service
public class AWCUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserAWC user = userRepository.findByUsername(username);

		if (user != null) {
			User.UserBuilder builder = User.withUsername(username).password(user.getPassword())
					.roles(user.getType().toString());
			return builder.build();

		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}

}
