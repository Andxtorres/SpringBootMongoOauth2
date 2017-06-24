package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.model.User;
import application.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	@Override
	public void insertUser(User user) {
		userRepository.insetUser(user);
		
	}
	@Override
	public User findByName(String name) {
		return userRepository.findUserByName(name);
	}
	@Override
	public void updateUser(User user) {
		userRepository.updateUser(user);
		
	}

}
