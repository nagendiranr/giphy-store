package com.org.giphystore.service;

import com.org.giphystore.model.Category;
import com.org.giphystore.model.User;

public interface CategoryService {
	void save(Category category, User user);
}
