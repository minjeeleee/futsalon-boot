package com.footsalon.team;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/team")
public class TeamController {

    private final TeamService teamService;

    @GetMapping(path = "/main")
    public void teamMain() {
    }


}
