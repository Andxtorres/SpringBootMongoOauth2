package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import application.model.User;
import application.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;


	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Load: " +username);
		User user= (User) userRepository.findUserByName(username);
		
		return user;
	}

}