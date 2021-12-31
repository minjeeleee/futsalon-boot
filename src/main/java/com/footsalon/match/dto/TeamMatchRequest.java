package com.footsalon.match.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamMatchRequest {

    private String localCode;
    private String placeName;
    private String placeAddress;
    private String matchStyle;
    private String matchDateTime;
    private String teamLevel;
    private String expense;
    private String content;

}
