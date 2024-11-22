package iuh.fit.se.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iuh.fit.se.entities.Role;
import iuh.fit.se.entities.User;
import iuh.fit.se.repositories.RoleRepository;
import iuh.fit.se.repositories.UserRepository;
import iuh.fit.se.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    

	@Override
	public void save(User user) {
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
        	role = new Role();
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }
        
        user.setPassword(user.getPassword());
        
        user.setRoles(List.of(role));
        
        userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
