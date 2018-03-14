package com.pollapp.repository;

import com.pollapp.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserAccount findByUsername(String username);
}
