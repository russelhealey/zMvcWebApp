package org.rmcmj.service;

import java.util.List;
import java.util.UUID;

import org.rmcmj.domain.User;
import org.rmcmj.repository.RoleRepository;
import org.rmcmj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public User create(User user) {
		user.setId(UUID.randomUUID().toString());
		user.getRole().setId(UUID.randomUUID().toString());
		//
		// // We must save both separately since there is no cascading feature
		// // in Spring Data MongoDB (for now)
		// roleRepository.save(user.getRole());
		// return userRepository.save(user);

		MongoOperations mo = (MongoOperations) mongoTemplate;
		mo.insert(user);

		MongoOperations mo2 = (MongoOperations) mongoTemplate;
		mo2.insert(user.getRole());
		return user;

	}

	public User read(User user) {
		// return user;

		MongoOperations mo = (MongoOperations) mongoTemplate;

		Query q = new Query(Criteria.where("username").is(user.getUsername()));
		List<User> find = mo.find(q, User.class);
		if (find != null && !find.isEmpty()) {
			return find.get(0);
		} else {
			return null;
		}
	}

	public List<User> readAll() {
		return userRepository.findAll();
	}

	public User update(User user) {
		// User existingUser =
		// userRepository.findByUsername(user.getUsername());
		//
		// if (existingUser == null) {
		// return null;
		// }
		//
		// existingUser.setFirstName(user.getFirstName());
		// existingUser.setLastName(user.getLastName());
		// existingUser.getRole().setRole(user.getRole().getRole());
		//
		// // We must save both separately since there is no cascading feature
		// // in Spring Data MongoDB (for now)
		// roleRepository.save(existingUser.getRole());
		// return userRepository.save(existingUser);

		MongoOperations mo = (MongoOperations) mongoTemplate;

		Query q = new Query(Criteria.where("username").is(user.getUsername()));
		List<User> find = mo.find(q, User.class);
		if (find != null && find.isEmpty()) {
			User user2 = find.get(0);
			user2.setFirstName(user.getFirstName());
			user2.setLastName(user.getLastName());
			user2.getRole().setRole(user.getRole().getRole());
			mo.save(user2);
			return user2;
		} else {
			return null;
		}
	}

	public Boolean delete(User user) {
		// User existingUser =
		// userRepository.findByUsername(user.getUsername());
		//
		// if (existingUser == null) {
		// return false;
		// }
		//
		// // We must delete both separately since there is no cascading feature
		// // in Spring Data MongoDB (for now)
		// roleRepository.delete(existingUser.getRole());
		// userRepository.delete(existingUser);
		// return true;

		MongoOperations mo = (MongoOperations) mongoTemplate;

		User existingBookmark = this.read(user);

		mo.remove(existingBookmark);
		return true;
	}
}
