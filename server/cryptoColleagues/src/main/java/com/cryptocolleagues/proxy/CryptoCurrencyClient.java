package com.cryptocolleagues.proxy;

import com.cryptocolleagues.dtos.CryptoCurrencies;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "crypto", url = "https://coinlore-cryptocurrency.p.rapidapi.com/api/tickers/?start=0&limit=100&rapidapi-key=28ee619730mshf9fcf6a8eddebcep16ab2bjsn1a3db9f6b5da")

public interface CryptoCurrencyClient {

    @GetMapping
    CryptoCurrencies getAllCryptoCurrencies();

}
