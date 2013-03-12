package org.rmcmj.repository;

import org.rmcmj.domain.Bookmark;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookmarkRepository extends MongoRepository<Bookmark, String> {

	Bookmark findByUrl(String url);

}
