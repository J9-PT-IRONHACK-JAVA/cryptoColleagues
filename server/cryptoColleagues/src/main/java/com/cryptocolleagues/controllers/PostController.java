package com.cryptocolleagues.controllers;

import com.cryptocolleagues.dtos.PostDto;
import com.cryptocolleagues.models.Post;
import com.cryptocolleagues.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Post createPost(@RequestBody PostDto postDto) throws Exception {
        return postService.create(postDto);
    }
}
