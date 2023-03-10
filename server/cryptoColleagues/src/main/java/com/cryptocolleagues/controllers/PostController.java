package com.cryptocolleagues.controllers;

import com.cryptocolleagues.dtos.PostRequest;
import com.cryptocolleagues.models.Post;
import com.cryptocolleagues.services.PostService;
import com.cryptocolleagues.utils.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Tag(name = "Post")
public class PostController {
    private final PostService postService;

    @RequestMapping(path="/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get all Post")
    public ResponseEntity<?> getAllPost() {
        var posts = postService.getAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @RequestMapping(path="/post/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get selected Post")
    public ResponseEntity<?> getPost(@PathVariable("id") Long id) {
        var post = postService.getById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Post", security = @SecurityRequirement(name = "bearerAuth"))
    public Post createPost(@RequestBody @Valid PostRequest postRequest) throws Exception {
        return postService.create(postRequest);
    }

    @RequestMapping(path="/post/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete Post", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
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
    public ResponseEntity<?>  updatePost(@PathVariable("id") Long id, @RequestBody @Valid PostRequest postRequest) {
        try {
            var updatedPost =  postService.updatePost(id, postRequest);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } catch(Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Post cannot be updated");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    @PatchMapping("/post/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update author Post", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?>  updateAuthorPost(@PathVariable("id") Long id, @RequestParam Optional<Long> author) {
        try {
            var updatedAuthorPost =  postService.updateAuthorPost(id, author);
            return new ResponseEntity<>(updatedAuthorPost, HttpStatus.OK);
        } catch(Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Author Post cannot be updated");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }
}
