package com.cryptocolleagues.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostRequest {
    private String title;
    private String description;
    private String content;
}
