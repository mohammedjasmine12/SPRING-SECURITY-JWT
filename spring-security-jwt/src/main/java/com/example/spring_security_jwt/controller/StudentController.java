package com.example.spring_security_jwt.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping
    public String getStudents() {

        return "Student list - JWT authentication successful";
    }

    @GetMapping("/{id}")
    public String getStudent(
            @PathVariable Long id) {

        return "Student ID: " + id;
    }
}