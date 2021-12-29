package com.footsalon.team;

import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.exception.HandlableException;
import com.footsalon.common.util.file.FileInfo;
import com.footsalon.common.util.file.FileUtil;
import com.footsalon.location.Location;
import com.footsalon.location.LocationService;
import com.footsalon.team.dto.TeamRequest;
import com.footsalon.teamApplicant.TeamApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;
    private final LocationService locationService;
    private final TeamApplicantService teamApplicantService;

    @Transactional
    public void createTeam(TeamRequest request, MultipartFile teamFile) {
        Location location = locationService.findLocation(request.getLocalCode());

        Team team = Team.createTeam(request);
        team.setLocation(location);

        if(!teamFile.isEmpty()) {
            System.out.println("==========================");
            FileUtil fileUtil = new FileUtil();
            FileInfo fileInfo = fileUtil.fileUpload(teamFile);
            team.setFile(fileInfo);
        }
        teamRepository.save(team);
    }

    public String tmNameCheck(String tmName) {
        Team team = teamRepository.findByTmName(tmName);
        if(team == null) {
            return "available";
        } else {
            return "disable";
        }
    }

    public Team findTeamById(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(()->new HandlableException(ErrorCode.TEAM_DOES_NOT_EXIST));
        return team;
    }

}
