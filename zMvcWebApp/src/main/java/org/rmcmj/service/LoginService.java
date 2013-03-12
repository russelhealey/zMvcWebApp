package org.rmcmj.service;

import java.util.List;

import org.rmcmj.domain.MenuItem;
import org.rmcmj.domain.User;
import org.rmcmj.repository.MenuItemRepository;
import org.rmcmj.repository.RoleRepository;
import org.rmcmj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	public boolean login(User user){
		User existingUser = userRepository.findByUsername(user.getUsername());
		if(existingUser != null){
			return existingUser.getPassword().equals(user.getPassword());
		}
		return false;
	}
	
	public List<MenuItem> getAllMenuItems(){
		return menuItemRepository.findAll();
	}
	
}
