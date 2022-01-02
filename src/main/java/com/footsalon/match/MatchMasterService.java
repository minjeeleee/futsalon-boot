package com.footsalon.match;

import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.exception.HandlableException;
import com.footsalon.location.Location;
import com.footsalon.location.LocationService;
import com.footsalon.match.dto.RatingRequest;
import com.footsalon.match.dto.ResultRequest;
import com.footsalon.match.dto.TeamMatchRequest;
import com.footsalon.matchGame.MatchGame;
import com.footsalon.matchGame.MatchGameService;
import com.footsalon.member.model.service.MemberService;
import com.footsalon.result.Result;
import com.footsalon.result.ResultService;
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
    private final MatchGameService matchGameService;
    private final LocationService locationService;
    private final TeamService teamService;
    private final MemberService memberService;
    private final ResultService resultService;

    @Transactional
    public void createMatchMaster(TeamMatchRequest request, Long teamId) {
        Team team = teamService.findById(teamId);
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
        Team team = teamService.findById(tmIdx);
        if(matchMaster.getTeam() == team) {
            return "disable";
        }
        matchGameService.createMatchGame(matchMaster, team);
        return "available";
    }

    public List<MatchMaster> findMatchMastersByTeam(Team team) {
        return matchMasterRepository.findByTeam(team);
    }

    @Transactional
    public void saveResult(ResultRequest request) {
        Team team = teamService.findById(request.getTmIdx());
        MatchGame matchGame = matchGameService.findById(request.getMgIdx());
        Team winner;
        if(matchGame.getHomeTeam().getTmIdx() == team.getTmIdx()) {
            if (request.getResult().equals("win")) {
                winner = matchGame.getHomeTeam();
            } else {
                winner = matchGame.getAwayTeam();
            }
        } else {
            if (request.getResult().equals("win")) {
                winner = matchGame.getAwayTeam();
            } else {
                winner = matchGame.getHomeTeam();
            }
        }
        Result result = resultService.createResult(winner);
        matchGame.updateResult(result);

    }

    @Transactional
    public void saveRating(RatingRequest request) {
        Team team = teamService.findById(request.getTmIdx());
        MatchGame matchGame = matchGameService.findById(request.getMgIdx());
        String target = "";
        if (matchGame.getHomeTeam().getTmIdx() == team.getTmIdx()) {
            target = "awayTeam";
        } else {
            target = "homeTeam";
        }
        resultService.updateRating(matchGame.getResult().getThIdx(), target, request.getRating());
    }
}
