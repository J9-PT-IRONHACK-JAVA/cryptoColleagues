package com.cryptocolleagues.services;

import com.cryptocolleagues.dtos.CryptoCurrencyDto;
import com.cryptocolleagues.models.CryptoCurrency;
import com.cryptocolleagues.proxy.CryptoCurrencyClient;
import com.cryptocolleagues.repositories.CryptoCurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CryptoCurrencyService {

    private final CryptoCurrencyClient cryptoCurrencyClient;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    public List<CryptoCurrencyDto> getAll() {
        return cryptoCurrencyClient.getAllCryptoCurrencies().getData();
    }

    public CryptoCurrency saveCrypto(CryptoCurrency cryptoCurrency){
        return cryptoCurrencyRepository.save(cryptoCurrency);
    }
}
