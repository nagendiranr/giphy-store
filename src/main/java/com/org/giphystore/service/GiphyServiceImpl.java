package com.org.giphystore.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.giphystore.model.Giphy;
import com.org.giphystore.model.User;
import com.org.giphystore.repository.GiphyRepository;

@Service
public class GiphyServiceImpl implements GiphyService{

	@Autowired
	private GiphyRepository giphyRepository;
	
	@Override
	public void save(Giphy giphy, User user) throws Exception{		
		Set<User> userSet = new HashSet<User>(); 
		userSet.add(user);
		giphy.setUsers(userSet);
		giphyRepository.save(giphy);
	}
	
	@Override
	public void remove(Giphy giphy, User user) throws Exception{
		giphyRepository.deleteById(giphy.getId());
	}	
}
