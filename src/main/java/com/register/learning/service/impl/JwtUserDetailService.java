package com.register.learning.service.impl;

import com.register.learning.entity.BaseUser;
import com.register.learning.repositories.BaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private BaseUserRepository baseUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<BaseUser> baseUser = baseUserRepository.findByEmail(username);
        BaseUser user;
        user = baseUser.orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
