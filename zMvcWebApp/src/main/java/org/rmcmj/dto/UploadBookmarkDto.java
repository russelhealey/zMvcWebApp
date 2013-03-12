package org.rmcmj.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadBookmarkDto {
	private MultipartFile bookmarks;

	public MultipartFile getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(MultipartFile bookmarks) {
		this.bookmarks = bookmarks;
	}
}
