package application.repository;

import org.springframework.stereotype.Repository;

import application.model.User;
@Repository
public interface UserRepository {
	public void insetUser(User user);
	public User findUserByName(String name);
	public void updateUser(User user);
}
