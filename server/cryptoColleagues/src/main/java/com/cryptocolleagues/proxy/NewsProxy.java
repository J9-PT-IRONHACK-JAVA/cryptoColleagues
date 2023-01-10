package com.cryptocolleagues.proxy;

import com.cryptocolleagues.dtos.NewsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "NewsProxy", url = "https://api.thenewsapi.com/v1/news/all?api_token=XSXAA31P856tMIAlDnkvQ982gt8U2OaNIdVN80HQ&search=crypto,blockchain&language=en,es&published_after=2023-01-07")

public interface NewsProxy {
    @GetMapping
    NewsResponse getNews();

}
