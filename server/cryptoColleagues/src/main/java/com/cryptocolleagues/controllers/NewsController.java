package com.cryptocolleagues.controllers;

import com.cryptocolleagues.dtos.NewsResponse;
import com.cryptocolleagues.proxy.NewsProxy;
import feign.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {


    private final NewsProxy newsProxy;

  /*  @GetMapping
    public NewsResponse getAllNews(@RequestParam String api_token, @RequestParam LocalDate published_after){

        return newsProxy.getAllNews(api_token, published_after);
    }*/

    @GetMapping("/hello")
    public NewsResponse getNews(@RequestParam String api_token){
        api_token = "XSXAA31P856tMIAlDnkvQ982gt8U2OaNIdVN80HQ";
        return newsProxy.getNews(api_token);
    }
}
