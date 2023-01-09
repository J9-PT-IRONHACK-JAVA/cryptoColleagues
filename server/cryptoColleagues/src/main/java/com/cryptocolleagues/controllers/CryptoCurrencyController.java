package com.cryptocolleagues.controllers;

import com.cryptocolleagues.dtos.CryptoCurrencyDto;
import com.cryptocolleagues.services.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/cryptocurrencies")
@RequiredArgsConstructor
public class CryptoCurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('USER')")
    public List<CryptoCurrencyDto> getAllCryptos() {
        return cryptoCurrencyService.getAll();
    }

    // TODO: addCryptoCurrencyToPortfolio()
}
