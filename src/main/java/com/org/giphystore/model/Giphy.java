package com.org.giphystore.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "giphy")
@Getter
@Setter
@ToString
public class Giphy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String giphyId;
	private String url;
	
	@ManyToOne
	private Category category; 
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_giphy", joinColumns = @JoinColumn(name = "giphy_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;
}
