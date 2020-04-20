package com.org.giphystore.service;

import com.org.giphystore.model.User;

public interface UserService {	
    void save(User user) throws Exception;
    User findByUsername(String username) throws Exception;
}
