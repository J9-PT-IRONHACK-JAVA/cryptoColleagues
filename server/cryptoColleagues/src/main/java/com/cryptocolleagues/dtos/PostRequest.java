package com.cryptocolleagues.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostRequest {
    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 500, message = "The description can contain a maximum of 500 characters")
    private String description;

    @NotBlank
    @Size(max = 2500, message = "The content can contain a maximum of 2500 characters")
    private String content;
}
