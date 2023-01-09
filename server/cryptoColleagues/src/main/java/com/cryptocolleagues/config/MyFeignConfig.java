package com.cryptocolleagues.config;

import com.cryptocolleagues.proxy.NewsProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class MyFeignConfig {

    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
        return new HttpMessageConverters(additional);
    }

   /* @Bean
    public NewsProxy newsProxy(){

        return (api_token, published_after) -> null;

        //return published_after -> null, api_key;
    }*/


}
