package com.example.library.controller;

import com.example.library.dto.BookReqDto;
import com.example.library.dto.BookResDto;
import com.example.library.dto.RentResponseDto;
import com.example.library.dto.ResultDto;
import com.example.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResultDto> addBooks(@RequestBody BookReqDto bookReqDto) throws Exception {
        return bookService.addBook(bookReqDto);
    }

    @GetMapping("/allBooks")
    public List<BookResDto> getAll() {
        return bookService.getAllBooks();
    }

    @DeleteMapping("/delete-Books/{id}")
    public ResponseEntity<ResultDto> deleteBook(@PathVariable("id") Integer id) {
        return bookService.delete(id);
    }

    @PutMapping("/edit-book")
    public ResponseEntity<BookResDto> editBook(@RequestBody BookReqDto dto) throws Exception {
        return bookService.editBook(dto);
    }

    @GetMapping("/rented-books")
    public List<RentResponseDto> rentedBooks(){
        return bookService.getRentalInfoForLibrarian();
    }

}
