package com.aukcje.service;

import com.aukcje.address.Address;
import com.aukcje.repositories.RoleRepository;
import com.aukcje.role.Role;
import com.aukcje.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.aukcje.repositories.UserRepository;

import java.util.List;

@Component
public class UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addWithDefaultRole(User user) {
        Role defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        Address address = user.getAddress();
        if (address != null) {
            userRepository.save(user);
        }
    }

    public User findByUserAccountName(String userAccountName) {
        return userRepository.findByUserAccountName(userAccountName);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public void deleteUserById(Long id) {
       userRepository.deleteById(id);
    }
}