package com.personal_project.issue_tracking_system.security;

import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal_project.issue_tracking_system.utils.ApiResponseWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthController(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtService jwtService,
            TokenBlacklistService tokenBlacklistService) {

        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseWrapper<String>> authenticate(
            @RequestBody AuthRequestDTO request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

            return ResponseEntity
                    .ok(new ApiResponseWrapper<>("Success generate token", jwtService.generateToken(userDetails)));

        } catch (BadCredentialsException e) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponseWrapper<>("Invalid Username or Password (002)", null));

        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<ApiResponseWrapper<String>> logout(@RequestHeader("Authorization") String authHeader) {

        String jwtToken = authHeader.substring(7);

        try {
            Duration remainingTime = jwtService.getRemainingExpirationTime(jwtToken);

            tokenBlacklistService.blacklistToken(jwtToken, remainingTime);

            return ResponseEntity.ok(new ApiResponseWrapper<>("Logout successful. Token invalidated.", null));

        } catch (Exception e) {
            log.error("Error during token blacklisting: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(new ApiResponseWrapper<>("Logout failed due to server error", null));
        }
    }
}