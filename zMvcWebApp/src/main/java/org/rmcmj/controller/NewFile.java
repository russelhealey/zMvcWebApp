package org.rmcmj.controller;

import org.apache.log4j.Logger;
import org.rmcmj.domain.Role;
import org.rmcmj.domain.User;
import org.rmcmj.dto.UserListDto;
import org.rmcmj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/NewFile")
public class NewFile {

	protected static Logger logger4J = Logger.getLogger("aop");
	
	@Autowired
	private UserService service;
	
	@RequestMapping
	public String getNewFilePage() {
		return "NewFile";
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody User update(
			@RequestParam String username,
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam Integer role) {
		System.out.println("update!!!!!!!!!!!!!!");
		logger4J.info("Update!!!!!!!!!!");
		Role existingRole = new Role();
		existingRole.setRole(role);
		
		User existingUser = new User();
		existingUser.setUsername(username);
		existingUser.setFirstName(firstName);
		existingUser.setLastName(lastName);
		existingUser.setRole(existingRole);
		
		return existingUser;
	}
	
	@RequestMapping(value="/records")
	public @ResponseBody UserListDto getUsers() {
		System.out.println("get all!!!!!!!!!!!!!!");
		logger4J.info("get all!!!!!!!!!!");
		UserListDto userListDto = new UserListDto();
		userListDto.setUsers(service.readAll());
		return userListDto;
	}
	
}
