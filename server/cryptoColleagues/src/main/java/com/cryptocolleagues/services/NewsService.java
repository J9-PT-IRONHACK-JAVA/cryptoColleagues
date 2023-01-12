package com.cryptocolleagues.services;

import com.cryptocolleagues.dtos.NewsResponse;
import com.cryptocolleagues.proxy.NewsProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsProxy newsProxy;


    public NewsResponse getAllNews(){
        return newsProxy.getNews();
    }

}
