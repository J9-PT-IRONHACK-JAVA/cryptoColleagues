package com.cryptocolleagues.services;

import com.cryptocolleagues.dtos.CryptoCurrencyDto;
import com.cryptocolleagues.models.CryptoCurrency;
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

    private final CryptoCurrencyService cryptoCurrencyService;


    public List<Portfolio> getPortfoliosForUser(Long userId) {
        return userPortfolioRepository.findByAuthorId(userId);
    }

    public Optional<Portfolio> getSinglePortfolioForUser(Long portfolioId) {
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
        userPortfolioRepository.findById(portfolioId)
                .ifPresent(portfolio -> portfolio.setCryptoCurrencies(null));
        userPortfolioRepository.deleteById(portfolioId);
    }

    public void addCryptoCurrencyToPortfolio(CryptoCurrencyDto cryptocurrency, Long portfolioId) {
        var userPortfolio = getSinglePortfolioForUser(portfolioId);

        if (userPortfolio.isPresent()) {
            var cryptoToSave = new CryptoCurrency();

            cryptoToSave.setName(cryptocurrency.getName());
            cryptoToSave.setSymbol(cryptocurrency.getSymbol());
            cryptoToSave.setCoinRank(cryptocurrency.getRank());
            cryptoToSave.setCoinPrice(cryptocurrency.getPriceUsd());
            cryptoToSave.setPortfolio(userPortfolio.get());

            cryptoCurrencyService.saveCrypto(cryptoToSave);

            userPortfolio.get().getCryptoCurrencies().add(cryptoToSave);
            userPortfolioRepository.save(userPortfolio.get());
        }
    }
}
