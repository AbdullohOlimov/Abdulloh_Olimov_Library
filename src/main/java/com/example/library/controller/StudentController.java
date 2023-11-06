package com.example.library.controller;

import com.example.library.dto.RentRequestDto;
import com.example.library.dto.RentResponseDto;
import com.example.library.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@Api(value = "Student API", description = "API for managing student-related operations")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/rent-book")
    @ApiOperation(value = "Rent a Book", notes = "Rent a book by providing necessary details.")
    public ResponseEntity<String> rentBook(@RequestBody RentRequestDto dto) {
        if (studentService.rentBook(dto)) {
            return ResponseEntity.ok("Book rented successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to rent the book");
        }
    }

    @GetMapping("/check/{id}")
    @ApiOperation(value = "Get Rental Information", notes = "Retrieve rental information for a student by ID.")
    public List<RentResponseDto> rentInfo(@PathVariable("id") Integer studentId){
        return studentService.getRentalInfoForStudent(studentId);
    }

}
