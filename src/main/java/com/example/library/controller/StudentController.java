package com.example.library.controller;

import com.example.library.dto.RentRequestDto;
import com.example.library.dto.RentResponseDto;
import com.example.library.service.StudentService;
import org.hibernate.event.spi.ResolveNaturalIdEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/rent-book")
    public ResponseEntity<String> rentBook(@RequestBody RentRequestDto dto) {
        if (studentService.rentBook(dto)) {
            return ResponseEntity.ok("Book rented successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to rent the book");
        }
    }

    @GetMapping("/check/{id}")
    public List<RentResponseDto> rentInfo(@PathVariable("id") Integer studentId){
        return studentService.getRentalInfoForStudent(studentId);
    }

}
