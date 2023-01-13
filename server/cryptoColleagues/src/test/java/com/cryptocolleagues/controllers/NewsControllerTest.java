package com.cryptocolleagues.controllers;

import com.cryptocolleagues.dtos.NewsResponse;

import com.cryptocolleagues.models.User;
import com.cryptocolleagues.security.services.UserDetailsImpl;
import com.cryptocolleagues.security.services.UserDetailsServiceImpl;
import com.cryptocolleagues.services.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@WebMvcTest(NewsController.class)
@WithMockUser(username="admin",roles={"USER","ADMIN"})
//@Import({JwtUtils.class, WebSecurityConfig.class, UserDetails.class})
@AutoConfigureMockMvc

class NewsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    NewsService newsService;

    @MockBean
    UserDetailsServiceImpl userDetailsServiceImpl;

    //Cookie cookie;

  /*  @BeforeEach
    void init() throws Exception {
        var request = new LoginRequest();
        request.setUsername("martina");
        request.setPassword("123456");
        var data = mockMvc.perform(post("/api/auth/signin")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        cookie = data.getResponse().getCookie("cryptoColleagues");
    }*/

    //.cookie(cookie);

  /*  @BeforeEach
    void init(){
        var user = new User();
        user.setUsername("admin");
        when(userDetailsServiceImpl.loadUserByUsername("alissia"))
                .thenReturn(UserDetailsImpl.build(user));
    }*/

    @Test
    void getAllNews() throws Exception {
        var news = new NewsResponse();
        when(newsService.getAllNews()).thenReturn(news);
        mockMvc.perform(get("/api/news/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

   /* public static String asJsonString(final Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        }
        catch(Exception e){
            throw new RuntimeException();
        }
    }*/

}