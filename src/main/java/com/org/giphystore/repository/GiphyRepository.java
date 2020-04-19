package com.org.giphystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.giphystore.model.Giphy;

public interface GiphyRepository extends JpaRepository<Giphy, Long>{
}
