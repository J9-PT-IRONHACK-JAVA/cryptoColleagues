package com.cryptocolleagues.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class PostDto {
    private String title;
    private String description;
    private String content;
}
