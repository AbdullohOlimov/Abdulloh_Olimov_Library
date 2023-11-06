package com.example.library.service;

import com.example.library.dto.ResultDto;

import org.springframework.http.ResponseEntity;


public interface TokenService {
    ResponseEntity<ResultDto> jwtToken(String username, String password);
}
