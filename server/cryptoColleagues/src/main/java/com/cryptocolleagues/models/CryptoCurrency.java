package com.cryptocolleagues.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @JsonBackReference
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    public CryptoCurrency(String name, String symbol, int coinRank, double coinPrice) {
        this.name = name;
        this.symbol = symbol;
        this.coinRank = coinRank;
        this.coinPrice = coinPrice;
    }

}
