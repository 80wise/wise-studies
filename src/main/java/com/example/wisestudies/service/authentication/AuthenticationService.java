package com.example.wisestudies.service.authentication;

import com.example.wisestudies.controller.AuthenticationResponse;
import com.example.wisestudies.dto.PersonDto;
import com.example.wisestudies.entity.Person;
import com.example.wisestudies.repository.PersonRepository;
import com.example.wisestudies.security.config.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final PersonRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(Person person) {
        // just in order to get an encoded password
        var user = Person.builder()
                .firstname(person.getFirstname())
                .middleName(person.getMiddleName())
                .lastname(person.getLastname())
                .email(person.getEmail())
                .password(passwordEncoder.encode(person.getPassword()))
                .role(person.getRole())
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(PersonDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // catch the right exception later one
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
