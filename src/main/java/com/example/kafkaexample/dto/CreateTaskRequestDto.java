package com.example.kafkaexample.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequestDto {

    @NotBlank(message = "Content is not allowed to be empty")
    @Size(min = 10, max = 400, message = "Content length should be between 10 and 400")
    private String content;

}
