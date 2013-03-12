package org.rmcmj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pageC")
public class PageCController {

	@RequestMapping
	public String getPageCPage() {
		return "pageC";
	}

}
