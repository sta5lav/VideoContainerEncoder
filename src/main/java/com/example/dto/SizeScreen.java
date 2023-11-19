package com.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SizeScreen {

    @NotNull
    @Min(value = 20, message = "Min value = 20")
    private int width;

    @NotNull
    @Min(value = 20, message = "Min value = 20")
    private int height;


}
