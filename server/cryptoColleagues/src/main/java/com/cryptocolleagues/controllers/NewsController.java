package com.cryptocolleagues.controllers;

import com.cryptocolleagues.dtos.NewsResponse;
import com.cryptocolleagues.proxy.NewsProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsProxy newsProxy;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('USER')")
    public NewsResponse getAllNews(){

        return newsProxy.getNews();
    }
}
