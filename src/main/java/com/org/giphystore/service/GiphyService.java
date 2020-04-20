package com.org.giphystore.service;

import com.org.giphystore.model.Giphy;
import com.org.giphystore.model.User;

public interface GiphyService {
	void save(Giphy giphy, User user) throws Exception;	
	void remove(Giphy giphy, User user) throws Exception;
}
