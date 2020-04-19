package com.org.giphystore.service;

import com.org.giphystore.model.User;

public interface UserService {	
    void save(User user);
    User findByUsername(String username);
}
