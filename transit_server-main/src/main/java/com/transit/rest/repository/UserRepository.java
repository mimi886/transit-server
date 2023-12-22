package com.transit.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transit.rest.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    
    Optional<User> findByEmail(String email);
    
}
