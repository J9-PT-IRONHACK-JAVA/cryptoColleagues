package com.cryptocolleagues.services;

import com.cryptocolleagues.dtos.PostRequest;
import com.cryptocolleagues.dtos.PostResponse;
import com.cryptocolleagues.exceptions.PostNotFoundException;
import com.cryptocolleagues.models.Post;
import com.cryptocolleagues.repositories.PostRepository;
import com.cryptocolleagues.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.xml.transform.Source;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();




    PropertyMap<Post, PostResponse> postMapping = new PropertyMap<>() {
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
        var post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);

        TypeMap<Post, PostResponse> typeMap = modelMapper.getTypeMap(Post.class, PostResponse.class);
        if (typeMap == null) { // if not  already added
            modelMapper.addMappings(postMapping);
        }

        return modelMapper.map(post, PostResponse.class);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }


    public Post updatePost(Long id, PostRequest postRequest) {
            var postResponse = postRepository.findById(id);
            if (postResponse.isPresent()){
                postResponse.get().setTitle(postRequest.getTitle());
                postResponse.get().setContent(postRequest.getContent());
                postResponse.get().setDescription(postRequest.getDescription());
                return saveOrUpdate(postResponse.get());
            }
            Post post = new Post();
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setDescription(postRequest.getDescription());
            return saveOrUpdate(post);
        }
}

