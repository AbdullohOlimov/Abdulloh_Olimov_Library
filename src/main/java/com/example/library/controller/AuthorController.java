package com.example.library.controller;

import com.example.library.dto.AuthorDto;
import com.example.library.dto.BookResDto;
import com.example.library.dto.ResultDto;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@Api(value = "/authors", description = "THIS API FOR AUTHORS OF THE BOOKS")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add an author", notes = "Adds a new author with the provided information")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    ResponseEntity<ResultDto> addAuthors(@RequestBody AuthorDto dto){
       return authorService.addAuthor(dto);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Get all authors", notes = "Retrieve a list of all authors.")
    ResponseEntity<List<AuthorDto>> getAll(){
        return authorService.getAll();
    }

    @GetMapping("get-books/{id}")
    @ApiOperation(value = "Get books by author ID", notes = "Retrieve a list of books written by the author with the given ID.")
    List<BookResDto> getBooksByAuthor(@PathVariable("id") Integer id){
        return bookService.getAllByAuthors(id);
    }
}
