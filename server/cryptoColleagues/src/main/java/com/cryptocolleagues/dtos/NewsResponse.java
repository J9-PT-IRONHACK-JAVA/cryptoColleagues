package com.cryptocolleagues.dtos;

import lombok.Data;

import java.util.List;

@Data
public class NewsResponse {
    List<News> data;

}

@Data
class News{

    private String title;

    private String description;

    private String snippet;

    private String url;

    private String published_at;

    private String source;

}

