package com.footsalon.match;

import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.exception.HandlableException;
import com.footsalon.location.Location;
import com.footsalon.location.LocationService;
import com.footsalon.match.dto.*;
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
import java.util.ArrayList;
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
    public void createTeamMatchMaster(TeamMatchRequest request, Long teamId) {
        Team team = teamService.findById(teamId);
        Location location = locationService.findLocation(request.getLocalCode());
        MatchMaster matchMaster = MatchMaster.createTeamMatchMaster(request, location, team);
        matchMasterRepository.save(matchMaster);
    }

    @Transactional
    public void createMercenaryMatchMaster(MercenaryMatchRequest request, Long teamId) {
        Team team = teamService.findById(teamId);
        Location location = locationService.findLocation(request.getLocalCode());
        MatchMaster matchMaster = MatchMaster.createMercenaryMatchMaster(request, location, team);
        matchMasterRepository.save(matchMaster);
    }

    public List<MatchMaster> findMatchBoardList(String sort) {
        List<MatchMaster> matchMasterList = new ArrayList<>();
        if (sort.equals("") || sort.equals("latest")) {
            matchMasterList = matchMasterRepository.findByStateAndMercenaryCntAndMatchDateTimeAfter(0, 0, LocalDateTime.now().plusHours(4L), Sort.by(Sort.Direction.DESC, "regDate"));
        } else if (sort.equals("matchDate")) {
            matchMasterList = matchMasterRepository.findByStateAndMercenaryCntAndMatchDateTimeAfter(0, 0, LocalDateTime.now().plusHours(4L), Sort.by(Sort.Direction.ASC, "matchDateTime"));
        }
        for (MatchMaster matchMaster : matchMasterList) {
            Team team = matchMaster.getTeam();
            matchMaster.setTeam(teamService.findTeamWithScoreById(team.getTmIdx()));
        }
        return matchMasterList;
    }

    public List<MatchMaster> searchTeamMatchMaster(SearchRequest request) {
        List<MatchMaster> matchMasterList = new ArrayList<>();
        if (request.getLocalCode() != null && request.getLevel() == null) {
            Location location = locationService.findLocation(request.getLocalCode());
            matchMasterList = matchMasterRepository.findByStateAndMercenaryCntAndLocation(0, 0, location, Sort.by(Sort.Direction.DESC, "regDate"));
        } else if (request.getLocalCode() == null && request.getLevel() != null) {
            matchMasterList = matchMasterRepository.findByStateAndMercenaryCntAndTeamLevel(0, 0, request.getLevel(), Sort.by(Sort.Direction.DESC, "regDate"));
        } else if (request.getLocalCode() != null && request.getLevel() != null) {
            Location location = locationService.findLocation(request.getLocalCode());
            matchMasterList = matchMasterRepository.findByStateAndMercenaryCntAndTeamLevelAndLocation(0, 0, request.getLevel(), location, Sort.by(Sort.Direction.DESC, "regDate"));
        }
        for (MatchMaster matchMaster : matchMasterList) {
            Team team = matchMaster.getTeam();
            matchMaster.setTeam(teamService.findTeamWithScoreById(team.getTmIdx()));
        }
        return matchMasterList;
    }

    public List<MatchMaster> searchMercenaryMatchMaster(SearchRequest request) {
        List<MatchMaster> matchMasterList = new ArrayList<>();
        if (request.getLocalCode() != null && request.getLevel() == null) {
            Location location = locationService.findLocation(request.getLocalCode());
            matchMasterList = matchMasterRepository.findByStateAndMercenaryCntNotAndLocation(0, 0, location, Sort.by(Sort.Direction.DESC, "regDate"));
        } else if (request.getLocalCode() == null && request.getLevel() != null) {
            matchMasterList = matchMasterRepository.findByStateAndMercenaryCntNotAndTeamLevel(0, 0, request.getLevel(), Sort.by(Sort.Direction.DESC, "regDate"));
        } else if (request.getLocalCode() != null && request.getLevel() != null) {
            Location location = locationService.findLocation(request.getLocalCode());
            matchMasterList = matchMasterRepository.findByStateAndMercenaryCntNotAndTeamLevelAndLocation(0, 0, request.getLevel(), location, Sort.by(Sort.Direction.DESC, "regDate"));
        }
        for (MatchMaster matchMaster : matchMasterList) {
            Team team = matchMaster.getTeam();
            matchMaster.setTeam(teamService.findTeamWithScoreById(team.getTmIdx()));
        }
        return matchMasterList;
    }

    public Object findMercenaryMatchBoardList() {
        List<MatchMaster> matchMasterList = matchMasterRepository.findByStateAndMercenaryCntNotAndMatchDateTimeAfter(0, 0, LocalDateTime.now().plusHours(4L), Sort.by(Sort.Direction.DESC, "regDate"));
        for (MatchMaster matchMaster : matchMasterList) {
            Team team = matchMaster.getTeam();
            matchMaster.setTeam(teamService.findTeamWithScoreById(team.getTmIdx()));
        }
        return matchMasterList;
    }

    public List<MatchMaster> findMercenaryMatchBoardList(String sort) {
        List<MatchMaster> matchMasterList = new ArrayList<>();
        if (sort.equals("") || sort.equals("latest")) {
            matchMasterList = matchMasterRepository.findByStateAndMercenaryCntNotAndMatchDateTimeAfter(0, 0, LocalDateTime.now().plusHours(4L), Sort.by(Sort.Direction.DESC, "regDate"));
        } else if (sort.equals("matchDate")) {
            matchMasterList = matchMasterRepository.findByStateAndMercenaryCntNotAndMatchDateTimeAfter(0, 0, LocalDateTime.now().plusHours(4L), Sort.by(Sort.Direction.ASC, "matchDateTime"));
        }
        for (MatchMaster matchMaster : matchMasterList) {
            Team team = matchMaster.getTeam();
            matchMaster.setTeam(teamService.findTeamWithScoreById(team.getTmIdx()));
        }
        return matchMasterList;
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
        List<MatchMaster> matchMasterList = matchMasterRepository.findByTeam(team, Sort.by(Sort.Direction.DESC, "regDate"));
        for (MatchMaster matchMaster : matchMasterList) {
            matchMaster.setTeam(teamService.findTeamWithScoreById(matchMaster.getTeam().getTmIdx()));
        }
        return matchMasterList;
    }

    public List<MatchMaster> findMatchMastersByAwayTeam(Team team) {
        List<MatchMaster> matchMasterList = matchMasterRepository.findByAwayTeam(team);
        for (MatchMaster matchMaster : matchMasterList) {
            matchMaster.setTeam(teamService.findTeamWithScoreById(matchMaster.getTeam().getTmIdx()));
        }
        return matchMasterList;
    }

    @Transactional
    public void saveResult(ResultRequest request) {
        Team team = teamService.findById(request.getTmIdx());
        MatchGame matchGame = matchGameService.findById(request.getMgIdx());
        MatchMaster matchMaster = matchMasterRepository.findById(matchGame.getMatchMaster().getMmIdx()).orElseThrow(()->new HandlableException(ErrorCode.MATCH_MASTER_DOES_NOT_EXIST));
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
        matchMaster.setState(2);
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
