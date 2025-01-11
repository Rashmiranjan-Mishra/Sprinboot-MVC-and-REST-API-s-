package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;

@Entity  //it confirms tables in the DB
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")  //it is the table name in DB
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private int age;
    private LocalDate dateOfJoining;
    @JsonProperty ("isActive")
    private Boolean isActive;
    private String role;
    private Double Salary;

}
