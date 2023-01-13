package com.cryptocolleagues.services;

import com.cryptocolleagues.models.Portfolio;
import com.cryptocolleagues.repositories.UserPortfolioRepository;
import com.cryptocolleagues.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPortfolioService {

    private final UserPortfolioRepository userPortfolioRepository;

    private final UserRepository userRepository;

    public List<Portfolio> getPortfoliosForUser(Long userId) {
        return userPortfolioRepository.findByAuthorId(userId);
    }

    public Optional<Portfolio> getSinglePortfolioForUser(Long portfolioId){
        return userPortfolioRepository.findById(portfolioId);
    }

    public Portfolio saveOrUpdate(Portfolio portfolio) {
        return userPortfolioRepository.save(portfolio);
    }

    public Portfolio create(Portfolio portfolio) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var currentUser = authentication.getName();
        var author = userRepository.findByUsername(currentUser);
        var portfolioToSave = new Portfolio(portfolio.getName(), portfolio.getDescription(), author.get());
        return saveOrUpdate(portfolioToSave);
    }

    public void deletePortfolio(Long portfolioId) {
        userPortfolioRepository.deleteById(portfolioId);
    }
}
