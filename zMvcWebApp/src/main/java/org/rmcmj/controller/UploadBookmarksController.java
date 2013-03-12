package org.rmcmj.controller;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.rmcmj.domain.Bookmark;
import org.rmcmj.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/uploadBookmarks")
public class UploadBookmarksController {

	@Autowired
	private BookmarkService service;

	@RequestMapping
	public String getUploadBookmarksPage() {
		return "uploadBookmarks";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody
	String handleUploadViaTextArea(@RequestParam String bookmarks) {
		// logFile(form.getBookmarks().getName(),
		// form.getBookmarks().getSize());
		// return "redirect:/customer/account";

		InputStream inputStream = null;
		inputStream = new ByteArrayInputStream(bookmarks.getBytes());
		// inputStream = form.getBookmarks().getInputStream();
		XMLDecoder xmlDecoder = new XMLDecoder(inputStream);
		List<Bookmark> bookmarksList = (List<Bookmark>) xmlDecoder.readObject();

		service.deleteAll();
		service.saveAll(bookmarksList);

		return null;

	}
}
