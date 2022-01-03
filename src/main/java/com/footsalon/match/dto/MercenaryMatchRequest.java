package com.footsalon.match.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class MercenaryMatchRequest {

    @NotBlank
    private String localCode;

    @NotNull
    private String placeName;

    @NotNull
    private String placeAddress;

    @NotBlank
    private String matchStyle;

    @NotBlank
    private String matchDateTime;

    @NotBlank
    private String teamLevel;

    @NotBlank
    private String expense;

    @NotNull @Min(1)
    private int mercenaryCnt;

    private String content;

}
