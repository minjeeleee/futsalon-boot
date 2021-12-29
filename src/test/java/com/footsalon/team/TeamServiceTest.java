package com.footsalon.team;

import com.footsalon.team.dto.TeamRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class TeamServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TeamService teamService;
    
    @Test
    @DisplayName("팀ID로 팀찾기")
    public void findTeamById() throws Exception {
        Team team = teamService.findTeamById(1L);
    }

}