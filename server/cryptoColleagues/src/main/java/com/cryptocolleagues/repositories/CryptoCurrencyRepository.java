package com.cryptocolleagues.repositories;

import com.cryptocolleagues.models.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
}
