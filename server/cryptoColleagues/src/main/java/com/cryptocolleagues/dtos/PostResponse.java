package com.cryptocolleagues.dtos;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
public class PostResponse {
    private String title;
    private String description;
    private String content;
    private String userName;
    private String email;
}

