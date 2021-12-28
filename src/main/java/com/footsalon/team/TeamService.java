package com.footsalon.team;

import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.exception.HandlableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team findTeam(String tmCode) {
        return teamRepository.findById(tmCode).orElseThrow(()-> new HandlableException(ErrorCode.TEAM_DOES_NOT_EXIST));
    }
}
