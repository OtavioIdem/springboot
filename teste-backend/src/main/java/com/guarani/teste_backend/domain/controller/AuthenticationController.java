package com.guarani.teste_backend.domain.controller;

import com.guarani.teste_backend.domain.dto.AuthRequest;
import com.guarani.teste_backend.domain.dto.AuthResponse;
import com.guarani.teste_backend.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/registrar")
    public void registrar(@RequestBody AuthRequest request) {
        authService.registrar(request);
    }
}
