package com.example.library.service;

import com.example.library.dto.BookReqDto;
import com.example.library.dto.BookResDto;
import com.example.library.dto.RentResponseDto;
import com.example.library.dto.ResultDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    ResponseEntity<ResultDto> addBook(BookReqDto bookReqDto) throws Exception;
    List<BookResDto> getAllBooks();

    ResponseEntity<ResultDto> delete(Integer id);
    ResponseEntity<BookResDto> editBook(BookReqDto bookReqDto) throws Exception;
    List<BookResDto> getAllByAuthors(Integer id);

    List<RentResponseDto> getRentalInfoForLibrarian();
}
