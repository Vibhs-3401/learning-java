package com.register.learning.service.impl;

import com.register.learning.dto.BaseUserDto;
import com.register.learning.dto.LogInCredentialDto;
import com.register.learning.entity.BaseUser;
import com.register.learning.exception.AlreadyExist;
import com.register.learning.exception.InvalidSyntax;
import com.register.learning.exception.NoRecordFoundException;
import com.register.learning.repositories.BaseUserRepository;
import com.register.learning.service.iface.BaseUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BaseUserServiceImpl implements BaseUserService {

    public static final String REGEX_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @Autowired
    private BaseUserRepository baseUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean save(BaseUserDto baseUserDto, BindingResult bindingResult) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(baseUserDto.getEmail());
        if (matcher.matches()) {
            if (baseUserRepository.countByEmail(baseUserDto.getEmail()) == 0) {
                BaseUser baseUser = new BaseUser();
                modelMapper.map(baseUserDto, baseUser);
                baseUser.setPassword(bCryptPasswordEncoder.encode(baseUserDto.getPassword()));
                baseUser = baseUserRepository.save(baseUser);
                return true;
            } else {
                throw new AlreadyExist("email already exist");
            }
        } else {
            throw new InvalidSyntax("invalid email syntax");
        }
    }

    @Override
    public Boolean logIn(LogInCredentialDto logInCredentialDto, BindingResult bindingResult) {
        Optional<BaseUser> baseUserOptional = baseUserRepository.findByEmail(logInCredentialDto.getEmail());
        if (baseUserOptional.isPresent()) {
            if (baseUserOptional.get().getPassword().equals(logInCredentialDto.getPassword())) {
                return true;
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new NoRecordFoundException("No record found with this email, Please register");
        }
    }
}
