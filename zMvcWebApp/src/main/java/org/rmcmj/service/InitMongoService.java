package org.rmcmj.service;

import java.util.UUID;

import org.rmcmj.domain.MenuItem;
import org.rmcmj.domain.Role;
import org.rmcmj.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Service for initializing MongoDB with sample data using {@link MongoTemplate}
 */
public class InitMongoService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void init() {
		// Drop existing collections
		mongoTemplate.dropCollection("role");
		mongoTemplate.dropCollection("user");
		mongoTemplate.dropCollection("menuItem");
		// mongoTemplate.dropCollection("bookmark");

		// Create new records
		Role adminRole = new Role();
		adminRole.setId(UUID.randomUUID().toString());
		adminRole.setRole(1);

		Role userRole = new Role();
		userRole.setId(UUID.randomUUID().toString());
		userRole.setRole(2);

		User john = new User();
		john.setId(UUID.randomUUID().toString());
		john.setFirstName("Home");
		john.setLastName("Smith");
		john.setPassword("21232");
		john.setRole(adminRole);
		john.setUsername("john");

		User jane = new User();
		jane.setId(UUID.randomUUID().toString());
		jane.setFirstName("Jane");
		jane.setLastName("Adams");
		jane.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
		jane.setRole(userRole);
		jane.setUsername("jane");

		MenuItem menuItem = new MenuItem();
		menuItem.setGroupName("main");
		menuItem.setId(UUID.randomUUID().toString());
		menuItem.setName("User Maint.");
		menuItem.setUrl("users");

		MenuItem menuItem2 = new MenuItem();
		menuItem2.setGroupName("main");
		menuItem2.setId(UUID.randomUUID().toString());
		menuItem2.setName("Bookmark Maint.");
		menuItem2.setUrl("bookmarks");

		MenuItem menuItem3 = new MenuItem();
		menuItem3.setGroupName("main");
		menuItem3.setId(UUID.randomUUID().toString());
		menuItem3.setName("Bookmark Tree");
		menuItem3.setUrl("bookmarkTree");

		MenuItem menuItem4 = new MenuItem();
		menuItem4.setGroupName("main");
		menuItem4.setId(UUID.randomUUID().toString());
		menuItem4.setName("Table");
		menuItem4.setUrl("NewFile");

		MenuItem menuItem5 = new MenuItem();
		menuItem5.setGroupName("main");
		menuItem5.setId(UUID.randomUUID().toString());
		menuItem5.setName("Cs-Page");
		menuItem5.setUrl("pageC");

		MenuItem menuItem6 = new MenuItem();
		menuItem6.setGroupName("main");
		menuItem6.setId(UUID.randomUUID().toString());
		menuItem6.setName("Ms-Page");
		menuItem6.setUrl("pageM");

		MenuItem menuItem7 = new MenuItem();
		menuItem7.setGroupName("main");
		menuItem7.setId(UUID.randomUUID().toString());
		menuItem7.setName("Download Bookmarks");
		menuItem7.setUrl("download");
		// Bookmark bookmark = new Bookmark();
		// bookmark.setFolder("temp");
		// bookmark.setUrl("tempUrl");
		// bookmark.setId(UUID.randomUUID().toString());

		MenuItem menuItem8 = new MenuItem();
		menuItem8.setGroupName("main");
		menuItem8.setId(UUID.randomUUID().toString());
		menuItem8.setName("Upload Bookmarks");
		menuItem8.setUrl("uploadBookmarks");

		// Insert to db
		mongoTemplate.insert(john, "user");
		mongoTemplate.insert(jane, "user");
		mongoTemplate.insert(adminRole, "role");
		mongoTemplate.insert(userRole, "role");
		mongoTemplate.insert(menuItem, "menuItem");
		mongoTemplate.insert(menuItem2, "menuItem");
		mongoTemplate.insert(menuItem3, "menuItem");
		mongoTemplate.insert(menuItem4, "menuItem");
		mongoTemplate.insert(menuItem5, "menuItem");
		mongoTemplate.insert(menuItem6, "menuItem");
		mongoTemplate.insert(menuItem7, "menuItem");
		mongoTemplate.insert(menuItem8, "menuItem");
		// mongoTemplate.insert(bookmark, "bookmark");
	}
}
