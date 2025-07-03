package com.drew.synch.controllers;

import com.drew.synch.dtos.AuthRequestDTO;
import com.drew.synch.dtos.ResponseAPI;
import com.drew.synch.dtos.UserInputDTO;
import com.drew.synch.dtos.UserOutputDTO;
import com.drew.synch.entities.User;
import com.drew.synch.services.AuthenticationService;
import com.drew.synch.services.JwtService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationService authService;
    private final AuthenticationManager authManager;

    @PostMapping("/login")
    public ResponseEntity<ResponseAPI> login(@RequestBody AuthRequestDTO dto) {
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());

            Authentication auth = this.authManager.authenticate(usernamePassword);
            String token = jwtService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok().body(new ResponseAPI(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new ResponseAPI(e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseAPI> registerUserFirstStep(@RequestBody UserInputDTO dto) {
        UserOutputDTO user = authService.register(dto);
        return ResponseEntity.ok().body(new ResponseAPI(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI> getUserById(@Valid @PathVariable @NotNull Long id) {
        UserOutputDTO user = authService.getUserOutputById(id);
        return ResponseEntity.ok().body(new ResponseAPI(user));
    }

}
