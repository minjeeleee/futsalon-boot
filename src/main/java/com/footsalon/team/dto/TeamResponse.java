package com.footsalon.team.dto;

import com.footsalon.common.util.file.FileInfo;
import com.footsalon.location.Location;
import com.footsalon.team.Team;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.File;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class TeamResponse {

    private String tmName;
    private String teamLevel;
    private String tmInfo;
    private Location location;
    private FileInfo file;


    public static TeamResponse createResponse(Team team) {
        return TeamResponse.builder()
                .tmName(team.getTmName())
                .teamLevel(team.getTmLevel())
                .tmInfo(team.getTmInfo())
                .location(team.getLocation())
                .file(team.getFile())
                .build();
    }
}
