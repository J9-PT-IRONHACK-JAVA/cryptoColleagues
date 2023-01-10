package com.cryptocolleagues.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsResponse {
    List<News> data = new ArrayList<>();
}

@Data
class News{
    private String title;
    private String description;
    private String snippet;
    private String url;
    @JsonProperty("published_at")
    private String publishedAt;
    private String source;
}

