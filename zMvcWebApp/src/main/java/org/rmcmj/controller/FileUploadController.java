package org.rmcmj.controller;

import java.beans.XMLDecoder;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rmcmj.FileUpload;
import org.rmcmj.domain.Bookmark;
import org.rmcmj.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class FileUploadController extends SimpleFormController {

	@Autowired
	private BookmarkService service;

	public FileUploadController() {
		setCommandClass(FileUpload.class);
		setCommandName("fileUploadForm");
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		FileUpload file = (FileUpload) command;

		MultipartFile multipartFile = file.getFile();

		String fileName = "";

		if (multipartFile != null) {
			fileName = multipartFile.getOriginalFilename();
			// do whatever you want
			InputStream inputStream = null;
			try {
				// inputStream = form.getBookmarks().getInputStream();
				inputStream = file.getFile().getInputStream();
				XMLDecoder xmlDecoder = new XMLDecoder(inputStream);

				Bookmark bookmark = (Bookmark) xmlDecoder.readObject();
				if (bookmark != null) {
					service.deleteAll();
				}

				while (bookmark != null) {
					service.create(bookmark);
					try {
						bookmark = (Bookmark) xmlDecoder.readObject();
					} catch (Exception e) {
						break;
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return new ModelAndView("FileUploadSuccess", "fileName", fileName);
	}
}
