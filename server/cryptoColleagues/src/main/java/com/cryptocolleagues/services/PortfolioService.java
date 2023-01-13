package com.cryptocolleagues.services;

import com.cryptocolleagues.models.Portfolio;
import com.cryptocolleagues.repositories.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;

    public List<Portfolio> getAllPortfolios(){
        return portfolioRepository.findAll();
    }
}
