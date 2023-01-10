package com.cryptocolleagues.dtos;

import lombok.Data;

@Data
public class PostResponse {
    private String title;
    private String description;
    private String content;
    private String userName;
    private String email;
}
