package org.rmcmj.controller;

import org.rmcmj.dto.BookmarkListDto;
import org.rmcmj.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bookmarkTree")
public class BookmarkTreeController {

	@Autowired
	private BookmarkService service;

	@RequestMapping
	public String getUsersPage() {
		return "bookmarkTree";
	}

	@RequestMapping(value = "/records")
	public @ResponseBody
	BookmarkListDto getBookmarks() {
		BookmarkListDto bookmarkListDto = new BookmarkListDto();
		if (AuthenticationHelper.loggedIn()) {
			bookmarkListDto.setBookmarks(service.readAll());
		}
		return bookmarkListDto;
	}
}
