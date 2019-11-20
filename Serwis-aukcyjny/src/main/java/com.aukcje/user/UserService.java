package com.aukcje.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    Set<User> users = new HashSet<>();

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void add(User user) {
        userRepository.save(user);
    }


    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

}
