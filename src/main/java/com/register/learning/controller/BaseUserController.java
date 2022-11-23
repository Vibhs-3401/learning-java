package com.register.learning.controller;

import com.register.learning.dto.BaseUserDto;
import com.register.learning.dto.LogInCredentialDto;
import com.register.learning.service.iface.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class BaseUserController {

    @Autowired
    private BaseUserService baseUserService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody BaseUserDto baseUserDto, BindingResult bindingResult) {
        return ResponseEntity.ok(baseUserService.save(baseUserDto, bindingResult));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> singIn(@RequestBody LogInCredentialDto logInCredentialDto, BindingResult bindingResult) {
        return ResponseEntity.ok(baseUserService.logIn(logInCredentialDto, bindingResult));
    }
}
