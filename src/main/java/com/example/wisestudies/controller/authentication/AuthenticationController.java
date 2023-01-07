package com.example.wisestudies.controller.authentication;

import com.example.wisestudies.controller.AuthenticationResponse;
import com.example.wisestudies.service.authentication.AuthenticationService;
import com.example.wisestudies.dto.PersonDto;
import com.example.wisestudies.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody Person person
    ){
        return ResponseEntity.ok(authenticationService.register(person));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody PersonDto person
    ){
        return ResponseEntity.ok(authenticationService.authenticate(person));
    }

}
