package com.org.giphystore.model;

import java.io.Serializable;
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
public class Giphy implements Serializable {

	private static final long serialVersionUID = 8972418439829791042L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String giphyId;
	private String url;
	
	@ManyToOne
	private Category category; 
	
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "user_giphy", joinColumns = @JoinColumn(name = "giphy_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;
}
