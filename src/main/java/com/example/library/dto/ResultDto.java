package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    private Integer code;
    private String message;
    private Object data;

    public ResultDto(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public static ResultDto getSuccess(Object object) {
        return new ResultDto(200, "success", object);
    }


}
