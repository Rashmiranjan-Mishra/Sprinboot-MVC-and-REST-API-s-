package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDate;
import java.util.PrimitiveIterator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;
//    @NotNull(message = "Required field in Employee : Name")
//    @NotEmpty(message = "Name of the employee can not be empty")

    @NotBlank(message = "Name of the employee can not be blank")
    @Size(min = 3,max = 10,message = "Numbers of characters in the name should be in the range : [3,10]")
    private String name;

    @NotBlank(message = "Email of the employee can not be blank")
    @Email(message = "Email should be a Valid Email ")
    private String email;

    @Max(value = 80,message = "Age of Employee can not be greater than 80 ")
    @Min(value = 18,message = "Age of the Employee Can not be less than 18 ")
    private int age;

    @NotBlank(message = "Role of the employee can not be blank")
  //  @Pattern(regexp = "^(ADMIN|USER)$",message = "Role of Employee Can be either USER or ADMIN")
    @EmployeeRoleValidation  //Custom Annotations created
    private String role;//Admin,User

    @NotNull(message = "salary of the employee should not be null")
    @Positive(message = "salary of the employee should be positive"  )
    @Digits(integer = 6,fraction = 2,message = "The Salary can be in the form of XXXXX.YY ")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double Salary;

    @PastOrPresent(message = "Date of Joining field in Employee Can not be in the future ")
    private LocalDate dateOfJoining;

    @JsonProperty ("isActive")
    @AssertTrue(message = "isActive should be true")
    private Boolean isActive;




}
