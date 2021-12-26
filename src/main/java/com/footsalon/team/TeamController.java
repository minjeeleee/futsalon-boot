package com.footsalon.team;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;


}
