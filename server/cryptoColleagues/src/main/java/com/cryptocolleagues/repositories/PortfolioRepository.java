package com.cryptocolleagues.repositories;

import com.cryptocolleagues.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
