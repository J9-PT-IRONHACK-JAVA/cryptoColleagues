package com.cryptocolleagues.controllers;

import com.cryptocolleagues.services.CryptoCurrencyService;
import com.cryptocolleagues.utils.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/cryptocurrencies")
@RequiredArgsConstructor
@Tag(name = "CryptoCurrency")
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "get all cryptos")
    public ResponseEntity<?> getAllCryptos() {
        try {
            var allCryptoCurrencies = cryptoCurrencyService.getAll();
            return new ResponseEntity<>(allCryptoCurrencies, HttpStatus.OK);
        } catch (Exception exception) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Crypto cannot be obtained");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    // TODO: addCryptoCurrencyToPortfolio()
}
