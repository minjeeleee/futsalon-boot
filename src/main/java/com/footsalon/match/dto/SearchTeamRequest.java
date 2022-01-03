package com.footsalon.match.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchTeamRequest {

    private String localCode;
    private String teamLevel;

}
