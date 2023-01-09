package com.cryptocolleagues.controllers;

import com.cryptocolleagues.dtos.PostRequest;
import com.cryptocolleagues.services.PostService;
import com.cryptocolleagues.utils.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @RequestMapping(path="/post/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getPost(@PathVariable("id") int id) {
        var post = postService.getById((long) id);

        if(post == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Post not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Post", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        try {
            var createdPost= postService.create(postRequest);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch(Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Post cannot be created");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    @RequestMapping(path="/post/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete Post", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> deletePost(@PathVariable("id") int id) {
        try {
            postService.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Post cannot be deleted");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    @PutMapping("/post/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Post", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?>  updatePost(@PathVariable("id") Long id, @RequestBody PostRequest postRequest) {
        try {
            var updatedPost =  postService.updatePost(id, postRequest);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } catch(Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Post cannot be updated");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }
}
