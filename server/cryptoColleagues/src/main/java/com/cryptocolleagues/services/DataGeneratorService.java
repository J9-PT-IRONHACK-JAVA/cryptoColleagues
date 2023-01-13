package com.cryptocolleagues.services;

import com.cryptocolleagues.controllers.AuthController;
import com.cryptocolleagues.dtos.SignupRequest;
import com.cryptocolleagues.enums.RoleEnum;
import com.cryptocolleagues.models.Role;
import com.cryptocolleagues.models.User;
import com.cryptocolleagues.repositories.RoleRepository;
import com.cryptocolleagues.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Profile("demo")

public class DataGeneratorService {
    final Environment env;

    final RoleService roleServiceService;

    final RoleRepository roleRepository;

    final UserRepository userRepository;

    final AuthController authController;

    final PasswordEncoder encoder;

    @Bean
    public void loadFakeData(){
        try {
            loadDataRolesAndUsers();
        } catch (Exception e) {
            System.err.printf("Couldn't load fake data: %s\n", e.getMessage());
        }
    }

    private void loadDataRolesAndUsers() throws Exception {
        var roleAdmin = roleServiceService.create(RoleEnum.ROLE_ADMIN);
        var roleUser = roleServiceService.create(RoleEnum.ROLE_USER);

        var user1 = new User("demi", "demi@test.com", encoder.encode("123456"));
        user1.setRoles(Set.of(roleAdmin, roleUser));
        userRepository.save(user1);

        var user2 = new User("alissia", "alissia@test.com", encoder.encode("123456"));
        user2.setRoles(Set.of(roleAdmin, roleUser));
        userRepository.save(user2);

        var user3 = new User("martina", "martina@test.com", encoder.encode("123456"));
        user3.setRoles(Set.of(roleUser));
        userRepository.save(user3);
    }

        /* var user1 = new SignupRequest("demi", "demi@test.com", Set.of("admin"), "123456");
        authController.registerUser(user1);
        var user2 = new SignupRequest("alissia", "alissia@test.com", Set.of("admin", "user"), "123456");
        authController.registerUser(user2);
        var user3 = new SignupRequest("martina", "martina@test.com", Set.of("user"), "123456");
        authController.registerUser(user3);*/


}
