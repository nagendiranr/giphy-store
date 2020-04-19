package com.org.giphystore.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.giphystore.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	Set<Category> findByUserId(Long id);
}
