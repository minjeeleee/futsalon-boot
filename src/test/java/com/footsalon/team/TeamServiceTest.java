package com.footsalon.team;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TeamServiceTest {

    @Autowired
    TeamService teamService;
    
    @Test
    @DisplayName("팀 생성")
    public void createTeam() throws Exception {
        Team team = new Team();
        team.setTmCode(UUID.randomUUID().toString());
        team.setTmName("TeamA");
        team.setTmGrade("middle");
        team.setTmInfo("TeamA Info");
        Team savedTeam = teamService.createTeam(team);

        Team foundTeam = teamService.findTeam(team.getTmCode());

        assertThat(foundTeam).isEqualTo(savedTeam);
    }
}