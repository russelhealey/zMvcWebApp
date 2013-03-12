package org.rmcmj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/download")
public class Download {

	@RequestMapping
	public String getDownloadPage() {
		return "download";
	}

}
