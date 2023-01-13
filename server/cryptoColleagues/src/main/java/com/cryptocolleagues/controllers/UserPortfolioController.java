package com.cryptocolleagues.controllers;

import com.cryptocolleagues.dtos.CryptoCurrencyDto;
import com.cryptocolleagues.exceptions.ForbiddenException;
import com.cryptocolleagues.models.Portfolio;
import com.cryptocolleagues.repositories.UserPortfolioRepository;
import com.cryptocolleagues.repositories.UserRepository;
import com.cryptocolleagues.services.UserPortfolioService;
import com.cryptocolleagues.utils.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/portfolios")
@RequiredArgsConstructor
@Tag(name = "UserPortfolio")
public class UserPortfolioController {
    private final UserPortfolioService userPortfolioService;
    private final UserPortfolioRepository userPortfolioRepository;
    private final UserRepository userRepository;

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "get user portfolios")
    public ResponseEntity<?> getPortfolioForUser(@PathVariable Long userId) {
        try {
            var portfoliosForUser = userPortfolioService.getPortfoliosForUser(userId);
            return new ResponseEntity<>(portfoliosForUser, HttpStatus.OK);
        } catch(Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Portfolios for user cannot be obtained");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/one/{portfolioId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "get user portfolio")
    public ResponseEntity<?> getPortfoliosForUser(@PathVariable Long portfolioId) {
        try {
            var userPortfolio = userPortfolioService.getSinglePortfolioForUser(portfolioId);
            return new ResponseEntity<>(userPortfolio, HttpStatus.OK);
        } catch(Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Portfolio for user cannot be obtained");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "create user portfolios")
    public ResponseEntity<?> createPortfolio(@RequestBody Portfolio portfolio) {
        try {
            var createdUserPortfolio = userPortfolioService.create(portfolio);
            return new ResponseEntity<>(createdUserPortfolio, HttpStatus.OK);
        } catch(Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Portfolios for user cannot be created");
            return new ResponseEntity<>(errorResponse, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/delete/{portfolioId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "delete portfolio")
    public ResponseEntity<?> deletePortfolio(@PathVariable Long portfolioId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var currentUser = authentication.getName();
            var author = userRepository.findByUsername(currentUser);
            var portfolio = userPortfolioRepository.findById(portfolioId);
            if (portfolio.isPresent() && portfolio.get().getAuthor().equals(author.get())) {
                userPortfolioService.deletePortfolio(portfolioId);
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                throw new ForbiddenException();
            }
        } catch(Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Portfolios for user cannot be deleted");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/add/{portfolioId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "add crypto to portfolio")
    public ResponseEntity<?> addCryptoCurrencyToPortfolio(@RequestBody CryptoCurrencyDto cryptoCurrencyDto, @PathVariable Long portfolioId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var currentUser = authentication.getName();
            var author = userRepository.findByUsername(currentUser);
            var portfolio = userPortfolioRepository.findById(portfolioId);
            if (portfolio.isPresent() && portfolio.get().getAuthor().equals(author.get())) {
                userPortfolioService.addCryptoCurrencyToPortfolio(cryptoCurrencyDto, portfolioId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new ForbiddenException();
            }
        } catch (Exception exception) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Crypto cannot be added to portfolio");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
