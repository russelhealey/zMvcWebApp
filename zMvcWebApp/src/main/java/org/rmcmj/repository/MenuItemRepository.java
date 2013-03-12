package org.rmcmj.repository;

import org.rmcmj.domain.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuItemRepository extends MongoRepository<MenuItem, String>{

}
