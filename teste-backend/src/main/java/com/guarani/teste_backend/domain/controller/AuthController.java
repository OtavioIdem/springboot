package com.guarani.teste_backend.domain.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.guarani.teste_backend.domain.service.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // Aqui no projeto real, deveria autenticar usando AuthenticationManager
            // Aqui estamos apenas gerando token sem verificação de senha
            return tokenService.generateToken(userDetails);
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Credenciais inválidas");
        }
    }
}
