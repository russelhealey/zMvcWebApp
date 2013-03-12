package org.rmcmj.dto;

import java.util.List;

import org.rmcmj.domain.Bookmark;

public class BookmarkListDto {
	private List<Bookmark> bookmarks;

	public List<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(List<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

}
