package com.pollapp.repository;

import com.pollapp.entity.UserRole;
import com.pollapp.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole findByRole(Role role);
}
