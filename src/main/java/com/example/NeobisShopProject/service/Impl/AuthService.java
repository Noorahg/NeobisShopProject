package com.example.NeobisShopProject.service.Impl;


import com.example.NeobisShopProject.config.JwtService;
import com.example.NeobisShopProject.dto.AuthenticationRequest;
import com.example.NeobisShopProject.dto.AuthenticationResponse;
import com.example.NeobisShopProject.dto.RegistrationRequest;
import com.example.NeobisShopProject.entity.User;
import com.example.NeobisShopProject.enums.Role;
import com.example.NeobisShopProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegistrationRequest registrationRequest) {

        var user = User.builder()
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .role(Role.ROLE_USER )
                .build();

        userRepository.save(user);

        var jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userServiceImpl
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }
}
