package com.cryptocolleagues.controllers;


import com.cryptocolleagues.services.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@WithMockUser(username="admin",roles={"USER","ADMIN"})
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostService postService;

    @Test
    void getPostByIdTest() {
    }

    @Test
    void createPost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void updatePost() {
    }
}