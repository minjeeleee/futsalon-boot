package com.footsalon.team;

import com.footsalon.matchGame.MatchGame;
import com.footsalon.matchGame.MatchGameRepository;
import com.footsalon.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class TeamServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TeamService teamService;

    @Autowired
    MatchGameRepository matchGameRepository;
    
    @Test
    @DisplayName("팀멤버찾기")
    public void findTeamMember() throws Exception {
        Team team = teamService.findTeamById(7L);
        List<Member> memberList = team.getMemberList();
        for (Member member : memberList) {
            System.out.println("member = " + member.getUserId());
        }
    }

    @Test
    public void findMatchGame() throws Exception {
        Team team = teamService.findTeamById(1L);
//        List<MatchGame> matchGameList = matchGameRepository.findAllMatchGames(team.getTmIdx());
    }

}