package com.example.library.controller;

import com.example.library.dto.LibrarianReqDto;
import com.example.library.dto.ResultDto;
import com.example.library.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {
    private final TokenService tokenService;

    public LibrarianController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<ResultDto> getToken(@RequestBody LibrarianReqDto dto){
        return tokenService.jwtToken(dto.getUsername(), dto.getPassword());
    }


}
