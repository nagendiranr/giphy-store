package com.org.giphystore.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
public class User implements Serializable {
	
	private static final long serialVersionUID = 4492672577297856089L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    private String username;
    private String password;
    
    @Transient
    private String passwordConfirm;
    
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    
    @ManyToMany(mappedBy = "users") 
	private Set<Giphy> giphys;
    
    @OneToMany(mappedBy = "user")
    private Set<Category> categories;
        
}
