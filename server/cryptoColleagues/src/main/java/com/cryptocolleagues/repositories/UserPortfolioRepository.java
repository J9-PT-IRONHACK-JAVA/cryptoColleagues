package com.cryptocolleagues.repositories;

import com.cryptocolleagues.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserPortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByAuthorId(Long authorId);
    Optional<Portfolio> findById(Long portfolioId);

    @Modifying
    @Query(value = "delete from CryptoCurrency c where c.portfolio.id = :portfolioId")
    void deleteCryptoCurrenciesByPortfolioId(@Param("portfolioId") Long portfolioId);
}
