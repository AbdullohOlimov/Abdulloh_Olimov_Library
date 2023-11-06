package com.example.library.service;

import com.example.library.dto.AuthorDto;
import com.example.library.dto.BookResDto;
import com.example.library.dto.ResultDto;
import com.example.library.entity.Authors;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {
    ResponseEntity<ResultDto> addAuthor(AuthorDto authors);

    ResponseEntity<List<AuthorDto>> getAll();


}
