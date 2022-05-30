package com.sekoding.example.demo.controller;

import com.sekoding.example.demo.model.request.LoginRequest;
import com.sekoding.example.demo.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        Object data = loginService.loginUser(loginRequest);
        return ResponseEntity.ok(data);
    }

}
