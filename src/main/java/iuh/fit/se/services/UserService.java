package iuh.fit.se.services;

import java.util.List;

import iuh.fit.se.entities.User;

public interface UserService {
	public void save(User user);

	public User findByEmail(String email);

	public List<User> findAll();
}
