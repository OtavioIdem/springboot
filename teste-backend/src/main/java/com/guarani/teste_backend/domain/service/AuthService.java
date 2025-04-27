package com.guarani.teste_backend.domain.service;

import com.guarani.teste_backend.domain.dto.AuthRequest;
import com.guarani.teste_backend.domain.dto.AuthResponse;
import com.guarani.teste_backend.domain.entity.User;
import com.guarani.teste_backend.domain.repository.UserRepository;
import com.guarani.teste_backend.config.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtUtils.generateToken(user.getUsername());

        return new AuthResponse(token);
    }

    public void registrar(AuthRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("CLIENTE")
                .build();

        userRepository.save(user);
    }
}
