package application.service;

import org.springframework.stereotype.Service;

import application.model.User;

@Service
public interface UserService {
	
	public void insertUser(User user);
	public User findByName(String name);
	public void updateUser(User user);
}
