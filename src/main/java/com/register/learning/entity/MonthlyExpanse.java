package com.register.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyExpanse {

    @Id
    private Long id;

//    @JsonFormat()
    private LocalDate date;

    private Double credit;

    private Double debit;

    private String description;
}
