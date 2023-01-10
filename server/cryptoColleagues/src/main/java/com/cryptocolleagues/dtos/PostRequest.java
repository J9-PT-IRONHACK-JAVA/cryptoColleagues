package com.cryptocolleagues.dtos;

import lombok.Data;

@Data
public class PostRequest {
    private String title;
    private String description;
    private String content;
}
