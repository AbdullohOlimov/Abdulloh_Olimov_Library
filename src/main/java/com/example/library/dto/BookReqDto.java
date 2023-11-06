package com.example.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookReqDto {
    private Integer id;
    @JsonProperty("book_name")
    @NotBlank
    private String bookName;
    @JsonProperty("book_genre")
    @NotBlank
    private String bookGenre;

    @JsonProperty("count")
    @NotBlank
    private Integer count;
    @JsonProperty("author_id")
    @NotNull
    private List<Integer> authorId;
}
