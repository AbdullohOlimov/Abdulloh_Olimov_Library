package com.example.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentRequestDto {
    @JsonProperty("student_id")
    private Integer studentId;
    @JsonProperty("book_id")
    private Integer bookId;
    @JsonProperty("deadline")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date deadline;

}
