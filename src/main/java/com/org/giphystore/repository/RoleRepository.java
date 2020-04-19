package com.org.giphystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.giphystore.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
