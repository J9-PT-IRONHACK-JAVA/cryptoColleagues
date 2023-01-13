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

@Service
@RequiredArgsConstructor
public class UserPortfolioService {

    private final UserPortfolioRepository userPortfolioRepository;

    private final UserRepository userRepository;

    public List<Portfolio> getPortfoliosForUser(Long userId) {
        return userPortfolioRepository.findByAuthorId(userId);
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


    public void addCryptoCurrencyToPortfolio(CryptoCurrencyDto cryptoCurrencyDto, Long portfolioId) {
        // Find the desired portfolio by its id
        var userPortfolio = userPortfolioRepository.findById(portfolioId);
        if(userPortfolio.isPresent()){
            // Create a new CryptoCurrency object using the data from the CryptoCurrencyDto
            var cryptoToSave = new CryptoCurrency(cryptoCurrencyDto.getName(), cryptoCurrencyDto.getSymbol()
                    , cryptoCurrencyDto.getRank(), cryptoCurrencyDto.getPriceUsd());
            cryptoToSave.setPortfolio(userPortfolio.get());
            // Add the new CryptoCurrency object to the list of cryptoCurrencies in the Portfolio
            userPortfolio.get().getCryptoCurrencies().add(cryptoToSave);
            // Save the Portfolio
            userPortfolioRepository.save(userPortfolio.get());
        }
    }


}
