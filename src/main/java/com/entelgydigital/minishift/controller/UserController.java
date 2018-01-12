package com.entelgydigital.minishift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.entelgydigital.minishift.exception.UserIdMismatchException;
import com.entelgydigital.minishift.exception.UserNotFoundException;
import com.entelgydigital.minishift.model.User;
import com.entelgydigital.minishift.repo.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public Iterable<User> findAll() {
	return userRepository.findAll();
    }

    @GetMapping("/user/{username}")
    public List<User> findByUsername(@PathVariable String username) {
	return userRepository.findByUsername(username);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
	return userRepository.save(user);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable long id) {
	userRepository.findOne(id).orElseThrow(UserNotFoundException::new);
	userRepository.delete(id);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id) {
	if (user.getId() != id) {
	    throw new UserIdMismatchException();
	}
	userRepository.findOne(id).orElseThrow(UserNotFoundException::new);
	return userRepository.save(user);
    }

}