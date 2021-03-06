package com.org.giphystore.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString
public class Role implements Serializable {
	
	private static final long serialVersionUID = 8095584892661155502L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    private String name;
    
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    
}
