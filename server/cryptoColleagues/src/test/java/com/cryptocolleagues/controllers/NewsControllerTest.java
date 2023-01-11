package com.cryptocolleagues.controllers;

import com.cryptocolleagues.config.MyFeignConfig;
import com.cryptocolleagues.dtos.NewsResponse;

import com.cryptocolleagues.proxy.NewsProxy;
import com.cryptocolleagues.security.WebSecurityConfig;
import com.cryptocolleagues.security.jwt.JwtUtils;
import com.cryptocolleagues.security.services.UserDetailsServiceImpl;
import com.cryptocolleagues.services.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientFactory;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NewsController.class)
@EnableFeignClients
@Import({MyFeignConfig.class, WebSecurityConfig.class, FeignClient.class, FeignClientFactory.class, FeignClientProperties.class,})
class NewsControllerTest {

 /*   @Autowired
    MockMvc mockMvc;


    @MockBean
    NewsService newsService;

    @MockBean
    NewsProxy newsProxy;

    @Test
    void getAllNews() throws Exception {

        var news = new NewsResponse();
        when(newsProxy.getNews()).thenReturn(news);
        mockMvc.perform(get("/api/news/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }*/
}