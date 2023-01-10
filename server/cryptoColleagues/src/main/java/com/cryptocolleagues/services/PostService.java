package com.cryptocolleagues.services;

import com.cryptocolleagues.dtos.PostRequest;
import com.cryptocolleagues.dtos.PostResponse;
import com.cryptocolleagues.models.Post;
import com.cryptocolleagues.repositories.PostRepository;
import com.cryptocolleagues.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    PropertyMap<Post, PostResponse> postMapping = new PropertyMap<Post, PostResponse>() {
        protected void configure() {
            map().setUserName(source.getAuthor().getUsername());
            map().setEmail(source.getAuthor().getEmail());
        }
    };

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

    public PostResponse getById(Long id) {
        var post = postRepository.getById(id);
        modelMapper.addMappings(postMapping);
        var postResponse = modelMapper.map(post, PostResponse.class);
        return postResponse;
    }

    public void deleteById(int id) {
        postRepository.deleteById(id);
    }


    public Post updatePost(Long id, PostRequest postRequest) {
        var postResponse = getById(id);
        postResponse.setTitle(postRequest.getTitle());
        postResponse.setContent(postRequest.getContent());
        postResponse.setDescription(postRequest.getDescription());
        Post post = new Post();
        post.setTitle(postResponse.getTitle());
        post.setContent(postResponse.getContent());
        post.setDescription(postResponse.getDescription());
        return saveOrUpdate(post);
    }
}

