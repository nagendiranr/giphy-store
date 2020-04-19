package com.org.giphystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.giphystore.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
