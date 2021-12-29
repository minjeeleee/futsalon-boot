package com.footsalon.team;

import com.footsalon.team.dto.TeamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/team")
public class TeamController {

    private final TeamService teamService;

    @GetMapping(path = "/main")
    public void teamMain() {
    }

    @GetMapping(path = "/create")
    public void createTeam() {
    }

    @GetMapping(path = "/join")
    public void joinTeam() {
    }

    @GetMapping(path = "/tmName-check")
    @ResponseBody
    public String tmNameCheck(@RequestParam String tmName) {
        return teamService.tmNameCheck(tmName);
    }

    @PostMapping(path = "/create")
    public String createTeam(TeamRequest request, @RequestParam MultipartFile teamFile) {
        teamService.createTeam(request, teamFile);
        return "redirect:/team/main";
    }

    @GetMapping(path = "modify")
    public void modifyTeam(Model model) {
        Team team = teamService.findTeamById(1L);
        model.addAttribute("team", team);
    }

}
