package com.register.learning.service.iface;

import com.register.learning.dto.BaseUserDto;
import com.register.learning.dto.LogInCredentialDto;
import org.springframework.validation.BindingResult;

public interface BaseUserService {

    Boolean save(BaseUserDto baseUserDto, BindingResult bindingResult);

    Boolean logIn(LogInCredentialDto logInCredentialDto, BindingResult bindingResult);
}
