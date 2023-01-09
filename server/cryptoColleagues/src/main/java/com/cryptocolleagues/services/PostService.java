package com.cryptocolleagues.services;

import com.cryptocolleagues.dtos.PostRequest;
import com.cryptocolleagues.models.Post;
import com.cryptocolleagues.repositories.PostRepository;
import com.cryptocolleagues.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post saveOrUpdate(Post post) {
        return postRepository.save(post);
    }

    public Post create(PostRequest postRequest) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var currentUser = authentication.getName();
        var author = userRepository.findByUsername(currentUser);
        var postToSave = new Post(postRequest.getTitle(), postRequest.getDescription(), postRequest.getContent(), author.get());
        return saveOrUpdate(postToSave);
    }

    public Post getById(Long id) {
        return postRepository.getById(id);
    }

    public void deleteById(int id) {
        postRepository.deleteById(id);
    }

    public Post updatePost(Long id, PostRequest post) {
        var postToUpdate = getById(id);
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setContent(post.getContent());
        postToUpdate.setDescription(post.getDescription());
        return saveOrUpdate(postToUpdate);
    }
}
