package com.register.learning.service.impl;

import com.register.learning.dto.BaseUserDto;
import com.register.learning.entity.BaseUser;
import com.register.learning.repositories.BaseUserRepository;
import com.register.learning.service.iface.BaseUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BaseUserServiceImpl implements BaseUserService {

    public static final String REGEX_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @Autowired
    private BaseUserRepository baseUserRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean save(BaseUserDto baseUserDto, BindingResult bindingResult) {
        Pattern pattern =Pattern.compile(REGEX_EMAIL);
        Matcher matcher =pattern.matcher(baseUserDto.getEmail());
        if(matcher.matches()) {
            if(baseUserRepository.countByEmail(baseUserDto.getEmail())==0) {
                BaseUser baseUser = new BaseUser();
                modelMapper.map(baseUserDto, baseUser);
                baseUser = baseUserRepository.save(baseUser);
                if (baseUser != null) {
                    return true;
                }
            } else {
                throw new RuntimeException("email already exist");
            }
        } else {
            throw new RuntimeException("invalid email syntax");
        }
        return false;
    }
}
