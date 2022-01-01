package com.footsalon.match;

import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.exception.HandlableException;
import com.footsalon.location.Location;
import com.footsalon.location.LocationService;
import com.footsalon.match.dto.TeamMatchRequest;
import com.footsalon.member.Member;
import com.footsalon.member.model.service.MemberService;
import com.footsalon.team.Team;
import com.footsalon.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchMasterService {

    private final MatchMasterRepository matchMasterRepository;
    private final MatchGameRepository matchGameRepository;
    private final LocationService locationService;
    private final TeamService teamService;
    private final MemberService memberService;

    @Transactional
    public void createMatchMaster(TeamMatchRequest request, Long teamId) {
        Team team = teamService.findTeamById(teamId);
        Location location = locationService.findLocation(request.getLocalCode());
        MatchMaster matchMaster = MatchMaster.createMatchMaster(request, location, team);
        matchMasterRepository.save(matchMaster);
    }

    public List<MatchMaster> findMatchBoardList() {
        return matchMasterRepository.findByStateAndMatchDateTimeAfter(0, LocalDateTime.now().plusHours(4L), Sort.by(Sort.Direction.DESC, "regDate"));
    }

    @Transactional
    public String applyTeamMatch(Long mmIdx, Long tmIdx) {
        MatchMaster matchMaster = matchMasterRepository.findById(mmIdx).orElseThrow(()->new HandlableException(ErrorCode.MATCH_MASTER_DOES_NOT_EXIST));
        Team team = teamService.findTeamById(tmIdx);
        if(matchMaster.getTeam() == team) {
            return "disable";
        }
        MatchGame matchGame = MatchGame.createTeamMatchGame(matchMaster, team);
        matchGameRepository.save(matchGame);
        matchMaster.setState(1);
        return "available";
    }
}
