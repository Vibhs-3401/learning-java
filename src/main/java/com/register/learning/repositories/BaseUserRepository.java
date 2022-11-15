package com.register.learning.repositories;

import com.register.learning.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseUserRepository extends JpaRepository<BaseUser,String> {
    int countByEmail(String email);

    Optional<BaseUser> findByEmail(String email);
}
