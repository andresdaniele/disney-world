package com.alkemy.disneyWorld.auth.controller;

import com.alkemy.disneyWorld.auth.dto.AuthenticationRequest;
import com.alkemy.disneyWorld.auth.dto.AuthenticationResponse;
import com.alkemy.disneyWorld.auth.dto.UserDTO;
import com.alkemy.disneyWorld.auth.service.LoginService;
import com.alkemy.disneyWorld.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserDetailsCustomService userDetailCustomService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> signUp(@Valid @RequestBody UserDTO user) throws Exception {
        userDetailCustomService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authRequest) throws Exception {

        final String jwt =  loginService.userLogin(authRequest);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
