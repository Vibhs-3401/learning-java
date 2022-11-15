package com.register.learning.controller;

import com.register.learning.dto.BaseUserDto;
import com.register.learning.service.iface.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class BaseUserController {

    @Autowired
    private BaseUserService baseUserService;

    @PostMapping("/register")
    public Boolean register(@RequestBody BaseUserDto baseUserDto, BindingResult bindingResult) {
        return baseUserService.save(baseUserDto, bindingResult);
    }
}
