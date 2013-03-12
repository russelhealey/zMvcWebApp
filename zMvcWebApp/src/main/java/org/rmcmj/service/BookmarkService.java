package org.rmcmj.service;

import java.util.List;
import java.util.UUID;

import org.rmcmj.domain.Bookmark;
import org.rmcmj.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.DBCollection;

@Service
public class BookmarkService {
	@Autowired
	private BookmarkRepository bookmarkRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Bookmark> readAll() {
		// return bookmarkRepository.findAll();

		DBCollection collection = mongoTemplate.getCollection("bookmark");
		MongoOperations mo = (MongoOperations) mongoTemplate;
		return mo.findAll(Bookmark.class);
	}

	public Bookmark create(Bookmark bookmark) {
		bookmark.setId(UUID.randomUUID().toString());

		// We must save both separately since there is no cascading feature
		// in Spring Data MongoDB (for now)
		// return bookmarkRepository.save(bookmark);

		DBCollection collection = mongoTemplate.getCollection("bookmark");
		MongoOperations mo = (MongoOperations) mongoTemplate;
		mo.insert(bookmark);
		return bookmark;
	}

	public Bookmark read(Bookmark bookmark) {
		DBCollection collection = mongoTemplate.getCollection("bookmark");
		MongoOperations mo = (MongoOperations) mongoTemplate;

		Query q = new Query(Criteria.where("url").is(bookmark.getUrl()));
		List<Bookmark> find = mo.find(q, Bookmark.class);
		if (find != null && !find.isEmpty()) {
			return find.get(0);
		} else {
			return null;
		}
		// return bookmark;
	}

	public Bookmark update(Bookmark bookmark) {
		// Bookmark existingUser =
		// bookmarkRepository.findByUrl(bookmark.getUrl());
		//
		// if (existingUser == null) {
		// return null;
		// }
		//
		// existingUser.setFolder(bookmark.getFolder());
		//
		// return bookmarkRepository.save(existingUser);

		DBCollection collection = mongoTemplate.getCollection("bookmark");
		MongoOperations mo = (MongoOperations) mongoTemplate;

		Query q = new Query(Criteria.where("url").is(bookmark.getUrl()));
		List<Bookmark> find = mo.find(q, Bookmark.class);
		if (find != null && find.isEmpty()) {
			Bookmark bookmark2 = find.get(0);
			bookmark2.setFolder(bookmark.getFolder());
			mo.save(bookmark2);
			return bookmark2;
		} else {
			return null;
		}
	}

	public Boolean delete(Bookmark bookmark) {
		// Bookmark existingBookmark = bookmarkRepository.findByUrl(bookmark
		// .getUrl());
		//
		// if (bookmark == null) {
		// return false;
		// }

		// We must delete both separately since there is no cascading feature
		// in Spring Data MongoDB (for now)
		// bookmarkRepository.delete(existingBookmark);
		// return true;

		DBCollection collection = mongoTemplate.getCollection("bookmark");
		MongoOperations mo = (MongoOperations) mongoTemplate;

		Bookmark existingBookmark = this.read(bookmark);

		mo.remove(existingBookmark, "bookmark");

		return true;
	}

	public Boolean deleteAll() {
		MongoOperations mo = (MongoOperations) mongoTemplate;

		List<Bookmark> existingBookmark = this.readAll();
		for (Bookmark bookmark : existingBookmark) {
			mo.remove(bookmark);
		}
		return true;
	}

	public Boolean saveAll(List<Bookmark> bookmarks) {
		MongoOperations mo = (MongoOperations) mongoTemplate;
		for (Bookmark bookmark : bookmarks) {
			mo.insert(bookmark);
		}
		return true;
	}
}
