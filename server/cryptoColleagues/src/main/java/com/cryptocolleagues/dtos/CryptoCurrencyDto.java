package com.cryptocolleagues.dtos;

import com.cryptocolleagues.models.CryptoCurrency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CryptoCurrencyDto extends CryptoCurrency {
    private String name;
    private String symbol;
    private int rank;

    @JsonProperty("price_usd")
    private double priceUsd;
}

