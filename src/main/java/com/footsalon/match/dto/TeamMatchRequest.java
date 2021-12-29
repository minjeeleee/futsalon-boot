package com.footsalon.match.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamMatchRequest {

    private String localCode;
    private String placeName;
    private String placeAddress;
    private String size;
    private LocalDateTime matchDateTime;
    private String cost;
    private String level;
    private String content;

}
