package com.aukcje.user;

import com.aukcje.address.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    //Set<User> users = new HashSet<>();

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public void add(User user) {
        Address address = user.getAddress();
        if (address != null) {
            userRepository.save(user);
        }
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByUserAccountName(String userAccountName) {
        return userRepository.findByUserAccountName(userAccountName);

    }

}