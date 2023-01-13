package com.cryptocolleagues.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "portfolios")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @JsonManagedReference
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CryptoCurrency> cryptoCurrencies;

    public Portfolio(String name, String description, User author, List<CryptoCurrency> cryptoCurrencies) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.cryptoCurrencies = cryptoCurrencies;
    }

    public Portfolio(String name, String description, User author) {
        this.name = name;
        this.description = description;
        this.author = author;
    }
}
