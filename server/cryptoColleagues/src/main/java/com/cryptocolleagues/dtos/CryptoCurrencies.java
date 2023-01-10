package com.cryptocolleagues.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CryptoCurrencies {
    private List<CryptoCurrencyDto> data;
}
