package com.register.learning.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class MonthlyExpanse {

    @Id
    private Long id;

//    @JsonFormat()
    private LocalDate date;

    private Integer credit;

    private Integer debit;

    private String description;
}
