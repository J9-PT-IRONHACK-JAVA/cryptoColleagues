package com.cryptocolleagues.controllers;

import com.cryptocolleagues.exceptions.ForbiddenException;
import com.cryptocolleagues.models.Portfolio;
import com.cryptocolleagues.repositories.UserPortfolioRepository;
import com.cryptocolleagues.repositories.UserRepository;
import com.cryptocolleagues.services.UserPortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/portfolios")
@RequiredArgsConstructor
public class UserPortfolioController {
    private final UserPortfolioService userPortfolioService;
    private final UserPortfolioRepository userPortfolioRepository;
    private final UserRepository userRepository;

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Portfolio> getPortfoliosForUser(@PathVariable Long userId) {
        return userPortfolioService.getPortfoliosForUser(userId);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public Portfolio createPortfolio(@RequestBody Portfolio portfolio) {
        return userPortfolioService.create(portfolio);
    }

    @DeleteMapping("/delete/{portfolioId}")
    @PreAuthorize("hasRole('USER')")
    public void deletePortfolio(@PathVariable Long portfolioId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var currentUser = authentication.getName();
        var author = userRepository.findByUsername(currentUser);
        var portfolio = userPortfolioRepository.findById(portfolioId);
        if (portfolio.isPresent() && portfolio.get().getAuthor().equals(author.get())) {
            userPortfolioService.deletePortfolio(portfolioId);
        } else {
            throw new ForbiddenException();
        }
    }
}
