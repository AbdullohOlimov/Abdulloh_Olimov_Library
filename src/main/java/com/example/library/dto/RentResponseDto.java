package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentResponseDto {
    private Integer id;
    private String studentUsername;
    private String bookName;

    private Date rentedDay;
    private Long leftDays;
}
