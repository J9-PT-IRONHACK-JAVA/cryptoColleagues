package com.cryptocolleagues.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cryptocurrencies")
public class CryptoCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String symbol;
    private int coinRank;
    private double coinPrice;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    public CryptoCurrency(String name, String symbol, int coinRank, double coinPrice,Portfolio portfolio) {
        this.name = name;
        this.symbol = symbol;
        this.coinRank = coinRank;
        this.coinPrice = coinPrice;
        this.portfolio = portfolio;
    }
}
