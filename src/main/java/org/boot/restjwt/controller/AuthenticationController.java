package org.boot.restjwt.controller;

import lombok.RequiredArgsConstructor;
import org.boot.restjwt.dot.AuthenticationResponse;
import org.boot.restjwt.model.User;
import org.boot.restjwt.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User req) {
        return ResponseEntity.ok(authenticationService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User req) {
        return ResponseEntity.ok(authenticationService.authenticate(req));
    }

}
