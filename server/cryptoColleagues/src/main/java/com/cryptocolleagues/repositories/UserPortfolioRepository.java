package com.cryptocolleagues.repositories;

import com.cryptocolleagues.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByAuthorId(Long authorId);
}
