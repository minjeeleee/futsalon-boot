package com.footsalon.team.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class TeamRequest {

    @NotEmpty
    private String tmName;

    @NotEmpty
    private String tmInfo;

    @NotBlank
    private String tmGrade;

    @NotBlank
    private String localCode;

}
