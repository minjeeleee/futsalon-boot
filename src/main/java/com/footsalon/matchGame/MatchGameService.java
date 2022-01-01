package com.footsalon.matchGame;

import com.footsalon.match.MatchMaster;
import com.footsalon.match.MatchMasterService;
import com.footsalon.team.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchGameService {

    private final MatchGameRepository matchGameRepository;

    @Transactional
    public void createMatchGame(MatchMaster matchMaster, Team rivalTeam) {
        MatchGame matchGame = MatchGame.createTeamMatchGame(matchMaster, rivalTeam);
        matchGameRepository.save(matchGame);
        matchMaster.setState(1);
    }

    public List<MatchGame> findMyMatchGameList(Team myTeam) {
        return matchGameRepository.findAllByTmIdx(myTeam, myTeam);
    }
}
