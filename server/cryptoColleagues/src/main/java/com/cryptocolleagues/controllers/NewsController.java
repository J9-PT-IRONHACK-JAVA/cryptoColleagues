package com.cryptocolleagues.controllers;

import com.cryptocolleagues.proxy.NewsProxy;
import com.cryptocolleagues.services.NewsService;
import com.cryptocolleagues.utils.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
@Tag(name = "News")
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "get all news")
    public ResponseEntity<?> getAllNews(){
        try {
            var newsResponse = newsService.getAllNews();
            return new ResponseEntity<>(newsResponse, HttpStatus.OK);
        } catch(Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("News cannot be obtained");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }
}
