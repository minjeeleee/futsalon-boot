package com.footsalon.match;

import com.footsalon.location.Location;
import com.footsalon.location.LocationService;
import com.footsalon.match.dto.TeamMatchRequest;
import com.footsalon.team.Team;
import com.footsalon.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchMasterService {

    private final MatchMasterRepository matchMasterRepository;
    private final LocationService locationService;
    private final TeamService teamService;

    @Transactional
    public void createMatchMaster(TeamMatchRequest request, Long teamId) {
        Team team = teamService.findTeamById(teamId);
        Location location = locationService.findLocation(request.getLocalCode());
        MatchMaster matchMaster = MatchMaster.createMatchMaster(request, location, team);
        matchMasterRepository.save(matchMaster);
    }

    public List<MatchMaster> findIngMatchs() {
        return matchMasterRepository.findByState(0, Sort.by(Sort.Direction.DESC, "regDate"));
    }
}
