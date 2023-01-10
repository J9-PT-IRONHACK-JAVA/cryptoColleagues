package com.cryptocolleagues.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CryptoCurrencyDto {
    private String name;
    private String symbol;
    private int rank;

    @JsonProperty("price_usd")
    private double priceUsd;
}

