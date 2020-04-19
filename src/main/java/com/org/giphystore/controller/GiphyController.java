package com.org.giphystore.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

	@Autowired
	private GiphyService giphyService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Giphy giphy) {
		giphyService.save(giphy, getLoggedOnUser());
		return ResponseEntity.status(HttpStatus.CREATED).body("Added");
	}

	@DeleteMapping(value = "/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> remove(@RequestBody Giphy giphy) {
		giphyService.remove(giphy, getLoggedOnUser());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted");
	}
	
	@GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Set<Giphy>> get() {
		Set<Giphy> giphys = getLoggedOnUser().getGiphys(); 
		return	ResponseEntity.status(HttpStatus.OK).body(giphys); 
	}
	
	@GetMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Set<Giphy>> getGiphyByCategory(@RequestParam String categoryId) {
		Set<Giphy> giphys = getLoggedOnUser().getGiphys();
		Set<Giphy> newGiphys = new HashSet<Giphy>();
		for (Iterator<Giphy> iterator = giphys.iterator(); iterator.hasNext();) {		   
			Giphy giphy =  iterator.next();
		    if(giphy.getCategory().getId() == Long.parseLong(categoryId)) {
		    	newGiphys.add(giphy);
		    }		    
		}
		return	ResponseEntity.status(HttpStatus.OK).body(newGiphys); 
	}
	
	@PostMapping(value = "/category/add", consumes = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<String> addCategory(@RequestBody Category category) { 
		categoryService.save(category, getLoggedOnUser());
		return ResponseEntity.status(HttpStatus.CREATED).body("Added"); 
	}
	 
	@GetMapping(value = "/category/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Category>> getCategory()
	{
		Set<Category> categories = getLoggedOnUser().getCategories();
		return	ResponseEntity.status(HttpStatus.OK).body(categories);
	}
	 	
	private User getLoggedOnUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = userService.findByUsername(currentPrincipalName);
		return user;
	}
}
