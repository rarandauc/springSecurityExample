package com.example.security.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @GetMapping
    public String getAllEmployees(){
        return "You Received All Employees List";
    }

    @PostMapping
    public String saveEmployees(){
        return "You saved a Employee";
    }

    @PutMapping
    public String updateEmployees(){
        return "You updated a Employee";
    }

}

