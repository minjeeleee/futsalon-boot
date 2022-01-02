package com.footsalon.match.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ResultRequest {

    @NotNull
    private Long tmIdx;
    @NotNull
    private Long mgIdx;
    @NotBlank
    private String result;

}
