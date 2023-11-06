package com.example.library.controller;

import com.example.library.dto.AuthorDto;
import com.example.library.dto.BookResDto;
import com.example.library.dto.ResultDto;
import com.example.library.repository.BookRepository;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")

public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PostMapping("/add")
    ResponseEntity<ResultDto> addAuthors(@RequestBody AuthorDto dto){
       return authorService.addAuthor(dto);
    }

    @GetMapping("/getAll")
    ResponseEntity<List<AuthorDto>> getAll(){
        return authorService.getAll();
    }

    @GetMapping("get-books/{id}")
    List<BookResDto> getBooksByAuthor(@PathVariable("id") Integer id){
        return bookService.getAllByAuthors(id);
    }
}
