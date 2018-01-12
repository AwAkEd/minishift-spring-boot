package com.entelgydigital.minishift.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.entelgydigital.minishift.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
    List<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findOne(long id);
}