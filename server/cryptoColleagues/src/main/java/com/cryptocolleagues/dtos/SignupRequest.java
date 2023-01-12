package com.cryptocolleagues.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public SignupRequest() {
    }

    public SignupRequest(String username, String email, Set<String> role, String password) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }
}