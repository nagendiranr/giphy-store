package com.org.giphystore.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.giphystore.model.Category;
import com.org.giphystore.model.Giphy;
import com.org.giphystore.model.User;
import com.org.giphystore.service.CategoryService;
import com.org.giphystore.service.GiphyService;
import com.org.giphystore.service.UserService;

@RestController
@RequestMapping(value = "/giphy")
public class GiphyController {
	
	public static Logger logger = LoggerFactory.getLogger(GiphyController.class);

	@Autowired
	private GiphyService giphyService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Giphy giphy) throws Exception {
		giphyService.save(giphy, getLoggedOnUser());
		return ResponseEntity.status(HttpStatus.CREATED).body("Added");
	}

	@DeleteMapping(value = "/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> remove(@RequestBody Giphy giphy) throws Exception {
		giphyService.remove(giphy, getLoggedOnUser());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted");
	}
	
	@GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Set<Giphy>> get() throws Exception {
		Set<Giphy> giphys = getLoggedOnUser().getGiphys(); 
		return	ResponseEntity.status(HttpStatus.OK).body(giphys); 
	}
	
	@GetMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Set<Giphy>> getGiphyByCategory(@RequestParam String categoryId) throws Exception {
		Set<Giphy> giphys = getLoggedOnUser().getGiphys();
		Set<Giphy> newGiphys = new HashSet<Giphy>();
		//Iterate the list of user giphys and create new Set for matching category
		for (Iterator<Giphy> iterator = giphys.iterator(); iterator.hasNext();) {		   
			Giphy giphy =  iterator.next();
		    if(giphy.getCategory().getId() == Long.parseLong(categoryId)) {
		    	logger.debug("Matching Giphy ID:"+giphy.getGiphyId());
		    	newGiphys.add(giphy);
		    }		    
		}
		return	ResponseEntity.status(HttpStatus.OK).body(newGiphys); 
	}
	
	@PostMapping(value = "/category/add", consumes = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<String> addCategory(@RequestBody Category category) throws Exception { 
		categoryService.save(category, getLoggedOnUser());
		return ResponseEntity.status(HttpStatus.CREATED).body("Added"); 
	}
	 
	@GetMapping(value = "/category/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Category>> getCategory() throws Exception
	{
		Set<Category> categories = getLoggedOnUser().getCategories();
		return	ResponseEntity.status(HttpStatus.OK).body(categories);
	}
	 	
	private User getLoggedOnUser() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		logger.debug("Logged username:"+currentPrincipalName);
		User user = userService.findByUsername(currentPrincipalName);
		return user;
	}
}
