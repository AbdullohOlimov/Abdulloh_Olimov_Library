package com.example.library.mapper;

import com.example.library.dto.AuthorDto;
import com.example.library.entity.Authors;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public AuthorDto toDto(Authors authors){
        AuthorDto dto = new AuthorDto();
        dto.setId(authors.getId());
        dto.setFullName(authors.getAuthorName());
        return dto;
    }

    public Authors toEntity(AuthorDto dto){
        Authors authors = new Authors();
        authors.setAuthorName(dto.getFullName());
        return authors;
    }
}
