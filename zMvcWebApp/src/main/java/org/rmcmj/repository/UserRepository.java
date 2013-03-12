package org.rmcmj.repository;

import org.rmcmj.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findByUsername(String username);
}
