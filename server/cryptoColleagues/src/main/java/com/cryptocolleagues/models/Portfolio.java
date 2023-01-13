package com.cryptocolleagues.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_portfolios")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "portfolio")
    private List<CryptoCurrency> cryptoCurrencies;

    public Portfolio(String name, String description, User author) {
        this.name = name;
        this.description = description;
        this.author = author;
    }

    public Portfolio(String name, String description, User author, List<CryptoCurrency> cryptoCurrencies) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.cryptoCurrencies = cryptoCurrencies;
    }
}
