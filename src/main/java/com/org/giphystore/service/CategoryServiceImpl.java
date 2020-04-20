package com.org.giphystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.giphystore.model.Category;
import com.org.giphystore.model.User;
import com.org.giphystore.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void save(Category category, User user) throws Exception{
		category.setUser(user);
		categoryRepository.save(category);		
	}
}
