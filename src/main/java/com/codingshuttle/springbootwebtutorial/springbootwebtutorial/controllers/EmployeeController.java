package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController  //for REST API'S  HTTP request coming from Client's  //@RestController is the combination of controller and @ResponseBody , all the methods in the controller will return the JSON/XML directly to the Java Responsebody
@RequestMapping(path = "/employees")  //it is used to map the request Controller methods
public class EmployeeController {
//     @GetMapping(path = "/getSecretMessage")
//     public String getMySuperSecretMessage(){
//         return "Secret message : asdf#2345";
//     }
    //Injecting employee service through Constructor for Creating bean/object of EmployeeService class
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/{employeeID}")   //path variable is used when parameter is essential part of the URL
    public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable(name= "employeeID") Long ID){
        Optional<EmployeeDTO> employeeDTO= employeeService.getEmployeeById(ID);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(()-> new ResourceNotFoundException("Employee not Found with ID "+ID));
    }
    //ResponseEntity - all the methods returns Body , header and HTTP status to client


    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false,name ="inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){        //reuqestbody converts JSON to Java objects
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO,@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
         Boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
         if(gotDeleted) return ResponseEntity.ok(true);
         return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates
            ,@PathVariable Long employeeId){
        EmployeeDTO employeeDTO= employeeService.updatePartialEmployeeById(updates,employeeId);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

}
