package com.cryptocolleagues.controllers;

import com.cryptocolleagues.repositories.PortfolioRepository;
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
@RequestMapping("/api/portfolios")
@RequiredArgsConstructor
@Tag(name = "Portfolio")
public class PortfolioController {

    private final PortfolioRepository portfolioRepository;

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "get all logged user portfolios")
    public ResponseEntity<?>  getPortfoliosForUser(){
        try {
            var allPortfolios = portfolioRepository.findAll();
            return new ResponseEntity<>(allPortfolios, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Portfolios for logged user cannot be obtained");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }
}
