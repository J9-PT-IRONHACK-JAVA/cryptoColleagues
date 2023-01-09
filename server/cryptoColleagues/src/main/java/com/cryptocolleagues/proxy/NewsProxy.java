package com.cryptocolleagues.proxy;

import com.cryptocolleagues.dtos.NewsResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "news", url = "https://api.thenewsapi.com/v1/news/all")
public interface NewsProxy {


  /*  @GetMapping
    @Headers("Authorization: Basic {api_token}")
    NewsResponse getAllNews(@RequestParam String api_token, @RequestParam LocalDate published_after);*/

    @GetMapping
    NewsResponse getNews(@RequestParam String api_token);


}
