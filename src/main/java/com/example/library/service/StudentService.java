package com.example.library.service;

import com.example.library.dto.RentRequestDto;
import com.example.library.dto.RentResponseDto;

import java.util.List;

public interface StudentService {
    boolean rentBook(RentRequestDto dto);
    List<RentResponseDto> getRentalInfoForStudent(Integer id);
}
