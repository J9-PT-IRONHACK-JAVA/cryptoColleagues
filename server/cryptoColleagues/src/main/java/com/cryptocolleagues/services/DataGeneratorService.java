package com.cryptocolleagues.services;

import com.cryptocolleagues.enums.RoleEnum;
import com.cryptocolleagues.models.Role;
import com.cryptocolleagues.models.User;
import com.cryptocolleagues.repositories.RoleRepository;
import com.cryptocolleagues.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Profile("demo")
public class DataGeneratorService {
    final Environment env;

    final RoleService roleServiceService;

    final RoleRepository roleRepository;

    final UserRepository userRepository;

    @Bean
    public void loadFakeData(){
        try {
            loadRolesData();
            loadDataUsers();
        } catch (Exception e) {
            System.err.printf("Couldn't load fake data: %s\n", e.getMessage());
        }
    }

    private void loadRolesData() throws Exception {
        var roleAdmin = roleServiceService.create(RoleEnum.ROLE_ADMIN);
        var roleUser = roleServiceService.create(RoleEnum.ROLE_USER);
        System.out.println(roleAdmin);
        System.out.println(roleAdmin);
    }

    public void loadDataUsers(){
        var role = roleRepository.findByName(RoleEnum.ROLE_ADMIN);
        var user1 = new User("alissia", "alissia@fake.com", "123456");
        user1.setRoles(Set.of(role.get()));
        userRepository.save(user1);
    }
}
