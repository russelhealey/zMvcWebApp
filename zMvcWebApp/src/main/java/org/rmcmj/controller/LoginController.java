package org.rmcmj.controller;

import java.util.List;

import org.rmcmj.domain.User;
import org.rmcmj.dto.MenuItemListDto;
import org.rmcmj.dto.UserListDto;
import org.rmcmj.service.LoginService;
import org.rmcmj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

	private User user;

	@Autowired
	private UserService service;
	@Autowired
	private LoginService loginService;

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		return null;
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public @ResponseBody
	String processSubmit(@RequestParam String username,
			@RequestParam String password) {

		List<User> readAll = service.readAll();
		for (User user : readAll) {
			if (user.getUsername().equals(username)) {
				if (user.getPassword().equals(password)) {
					ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
							.currentRequestAttributes();
					UserListDto userListDto = new UserListDto();
					attr.getRequest().getSession(true)
							.setAttribute("allow", Boolean.TRUE);
				}
			}
		}
		return "Success";

	}

	@RequestMapping(value = "/menuLinks")
	public @ResponseBody
	MenuItemListDto getMenuItems(@ModelAttribute("user") User user) {
		MenuItemListDto menuItemListDto = new MenuItemListDto();
		menuItemListDto.setMenuItems(loginService.getAllMenuItems());

		return menuItemListDto;
	}

	@RequestMapping(value = "/authenticated")
	public @ResponseBody
	AuthenticationToken isAuthenticated() {
		return new AuthenticationToken(AuthenticationHelper.loggedIn());
	}
}
