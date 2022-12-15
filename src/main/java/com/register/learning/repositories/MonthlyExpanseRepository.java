package com.register.learning.repositories;

import com.register.learning.entity.MonthlyExpanse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyExpanseRepository extends JpaRepository<MonthlyExpanse, Long> {
}
