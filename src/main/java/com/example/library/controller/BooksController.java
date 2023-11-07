package com.example.library.controller;

import com.example.library.dto.BookReqDto;
import com.example.library.dto.BookResDto;
import com.example.library.dto.RentResponseDto;
import com.example.library.dto.ResultDto;
import com.example.library.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

@RestController
@RequestMapping("/books")
@Api(value = "Books API", description = "API for managing books")
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @ApiOperation(value = "Add a book", notes = "Adds a new book with the provided information.")
    public ResponseEntity<ResultDto> addBooks(@RequestBody BookReqDto bookReqDto) throws Exception {
        return bookService.addBook(bookReqDto);
    }

    @GetMapping("/allBooks")
    @ApiOperation(value = "Get all books", notes = "Retrieve a list of all books.")
    public List<BookResDto> getAll() {
        return bookService.getAllBooks();
    }

    @DeleteMapping("/delete-Books/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @ApiOperation(value = "Delete a book", notes = "Deletes a book with the given ID (requires admin role).")
    public ResponseEntity<ResultDto> deleteBook(@PathVariable("id") Integer id) {
        return bookService.delete(id);
    }

    @PutMapping("/edit-book")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @ApiOperation(value = "Edit a book", notes = "Edits the details of a book (requires admin role).")
    public ResponseEntity<BookResDto> editBook(@RequestBody BookReqDto dto) throws Exception {
        return bookService.editBook(dto);
    }

    @GetMapping("/rented-books")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @ApiOperation(value = "Get rented books", notes = "Retrieve a list of rented books.")
    public List<RentResponseDto> rentedBooks(){
        return bookService.getRentalInfoForLibrarian();
    }

}
