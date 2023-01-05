package com.cryptocolleagues.services;

import com.cryptocolleagues.enums.RoleEnum;
import com.cryptocolleagues.models.Role;
import com.cryptocolleagues.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void saveOrUpdate(Role role) {
        roleRepository.save(role);
    }

    public Role create(RoleEnum roleEnum) throws Exception {
        if (exists(roleEnum)) throw new Exception("Username already exists");
        var role = new Role(roleEnum);
        saveOrUpdate(role);
        return role;
    }

    public boolean exists(RoleEnum role){
        return roleRepository.existsByName(role);
    }
}
