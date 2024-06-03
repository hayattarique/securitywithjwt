package org.boot.restjwt.service;

import lombok.RequiredArgsConstructor;
import org.boot.restjwt.dot.AuthenticationResponse;
import org.boot.restjwt.model.User;
import org.boot.restjwt.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional(readOnly = false)
    public AuthenticationResponse register(User req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(user);
        user.setRole(req.getRole());
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(req.getUsername(), token);
    }

    @Transactional(readOnly = true)
    public AuthenticationResponse authenticate(User req) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        //User user = userRepository.findByUsername(req.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User Not Found:  " + req.getUsername()));
        //String token = jwtService.generateToken(user);
        UserDetails authPrincipal = (UserDetails) auth.getPrincipal();

        User user = (User) authPrincipal;
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(user.getUsername(), token);
    }

}
