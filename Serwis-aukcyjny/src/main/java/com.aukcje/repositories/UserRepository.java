package com.aukcje.repositories;

import com.aukcje.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserAccountName(String userAccountName);
    User findByLoginByEmail(String loginByEmail);

    void deleteById(Long id);
}
