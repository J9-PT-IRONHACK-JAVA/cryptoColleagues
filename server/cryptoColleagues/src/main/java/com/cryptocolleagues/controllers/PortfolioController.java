package com.cryptocolleagues.controllers;

import com.cryptocolleagues.models.Portfolio;
import com.cryptocolleagues.repositories.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/portfolios")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioRepository portfolioRepository;

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public List<Portfolio> getPortfoliosForUser(){
        return portfolioRepository.findAll();
    }
}
