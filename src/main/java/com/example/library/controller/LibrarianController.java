package com.example.library.controller;

import com.example.library.dto.LibrarianReqDto;
import com.example.library.dto.ResultDto;
import com.example.library.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/librarian")
@Api(value = "Librarian API", description = "API for managing librarian-related operations")
public class LibrarianController {
    private final TokenService tokenService;

    public LibrarianController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    @ApiOperation(value = "Get JWT Token", notes = "Generate a JWT token for librarian authentication.")
    public ResponseEntity<ResultDto> getToken(@RequestBody LibrarianReqDto dto){
        return tokenService.jwtToken(dto.getUsername(), dto.getPassword());
    }


}
