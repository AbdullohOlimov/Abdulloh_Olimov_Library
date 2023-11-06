package com.example.library.mapper;

import com.example.library.dto.AuthorDto;
import com.example.library.dto.BookReqDto;
import com.example.library.dto.BookResDto;
import com.example.library.entity.Authors;
import com.example.library.entity.Books;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class BookMapper {
    private final AuthorMapper mapper;
    public BookMapper(AuthorMapper mapper) {
        this.mapper = mapper;

    }

    public Books toEntity(BookReqDto bookReqDto, Set<Authors> set) throws Exception {
        return new Books(bookReqDto.getId(), bookReqDto.getBookName(), bookReqDto.getBookGenre(), bookReqDto.getCount(), set);

    }

    public BookResDto toDto(Books books){
        BookResDto dto = new BookResDto();
        dto.setId(books.getId());
        dto.setBookName(books.getBookName());
        dto.setBookGenre(books.getBookGenre());
        dto.setCount(books.getCount());
        Set<AuthorDto> authorResDtos = new HashSet<>();
        for (Authors author : books.getAuthors()) {
            authorResDtos.add(mapper.toDto(author));
        }
        dto.setAuthors(authorResDtos);
        return dto;
    }


}

