package org.rmcmj.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AuthenticationHelper {

	public static Boolean loggedIn() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		if (attr.getRequest().getSession(true).getAttribute("allow") != null) {
			return (Boolean) attr.getRequest().getSession(true)
					.getAttribute("allow");
		} else {
			return Boolean.FALSE;
		}
	}

}
