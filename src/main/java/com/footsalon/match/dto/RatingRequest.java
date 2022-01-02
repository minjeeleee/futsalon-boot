package com.footsalon.match.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RatingRequest {

    @NotNull
    private Long tmIdx;

    @NotNull
    private Long mgIdx;

    @NotNull @Min(1) @Max(5)
    private int rating;

}