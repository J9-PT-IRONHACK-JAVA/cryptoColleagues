package com.cryptocolleagues.services;

import com.cryptocolleagues.dtos.CryptoCurrencyDto;
import com.cryptocolleagues.proxy.CryptoCurrencyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CryptoCurrencyService {

    private final CryptoCurrencyClient cryptoCurrencyClient;

    public List<CryptoCurrencyDto> getAll() {
        return cryptoCurrencyClient.getAllCryptoCurrencies().getData();
    }

}
