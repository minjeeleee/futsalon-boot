package com.footsalon.match;

import com.footsalon.team.Team;
import com.footsalon.team.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MatchMasterServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MatchMasterRepository matchMasterRepository;

    @Autowired
    private TeamService teamService;

    @Test
    public void localDateTimeFormatterTest() throws Exception {
        String dateStr = "2021-12-30T19:23";
        dateStr = dateStr.replace("T", " ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
        System.out.println(date);
    }



}