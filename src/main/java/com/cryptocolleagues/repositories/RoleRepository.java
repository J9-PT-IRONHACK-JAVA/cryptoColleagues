package com.cryptocolleagues.repositories;

import com.cryptocolleagues.enums.RoleEnum;
import com.cryptocolleagues.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}