package org.rmcmj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pageM")
public class PageMController {

	@RequestMapping
	public String getPageMPage() {
		return "pageM";
	}

}
