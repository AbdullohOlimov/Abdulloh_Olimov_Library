package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResDto {
    private Integer id;
    private String bookName;
    private String bookGenre;
    private Integer count;
    private Set<AuthorDto> authors;
}
