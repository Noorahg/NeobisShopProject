package com.example.NeobisShopProject.controller;


import com.example.NeobisShopProject.dto.AuthenticationRequest;
import com.example.NeobisShopProject.dto.AuthenticationResponse;
import com.example.NeobisShopProject.dto.RegistrationRequest;
import com.example.NeobisShopProject.service.Impl.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Public entry point of the application")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return authService.register(registrationRequest);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/authentication")
    public AuthenticationResponse authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return authService.authenticate(request);
    }


}