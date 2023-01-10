package com.cryptocolleagues.services;

import com.cryptocolleagues.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
@Profile("demo")
public class DataGeneratorService {
    final Environment env;

    final RoleService roleServiceService;

    @Bean
    public void loadFakeData(){
        try {
            loadRolesData();
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
}
