package com.cryptocolleagues.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostRequest {
    @NotBlank(message = "Title must not be blank")
    private String title;

    @Size(min = 2, max = 500, message = "Length has to be between 2 and 500 characters")
    private String description;

    @Size(min = 2, max = 2500, message = "Length has to be between 2 and 2500 characters")
    private String content;
}
