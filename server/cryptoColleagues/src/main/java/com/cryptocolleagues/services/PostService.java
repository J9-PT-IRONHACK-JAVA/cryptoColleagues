package com.cryptocolleagues.services;

import com.cryptocolleagues.dtos.PostDto;
import com.cryptocolleagues.enums.RoleEnum;
import com.cryptocolleagues.models.Post;
import com.cryptocolleagues.models.Role;
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

    public Post create(PostDto postDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var currentUser = authentication.getName();
        var author = userRepository.findByUsername(currentUser);
        var postToSave = new Post(postDto.getTitle(), postDto.getDescription(), postDto.getContent(), author.get());
        return saveOrUpdate(postToSave);
    }
}
