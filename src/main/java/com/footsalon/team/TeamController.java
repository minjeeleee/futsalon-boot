package com.footsalon.team;

import com.footsalon.location.Location;
import com.footsalon.location.LocationService;
import com.footsalon.member.Member;
import com.footsalon.member.MemberAccount;
import com.footsalon.team.dto.TeamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/team")
public class TeamController {

    private final TeamService teamService;
    private final LocationService locationService;

    @GetMapping(path = "/main")
    public String teamMain(@AuthenticationPrincipal MemberAccount member) {
        if(member.getGrade().toString().equals("ME00")){ return "team/main"; }
        else { return "redirect:/team/modify"; }
    }

    @GetMapping(path = "/create")
    public void createTeam(Model model) {
        model.addAttribute("locations", locationService.findAllLocations());
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
    public String createTeam(@AuthenticationPrincipal MemberAccount memberAccount, TeamRequest request, @RequestParam MultipartFile teamFile) {
        teamService.createTeam(memberAccount.getMember(), request, teamFile);
        return "redirect:/team/main";
    }

    @GetMapping(path = "modify")
    public void modifyTeam(Model model, @AuthenticationPrincipal MemberAccount memberAccount) {
        Team team = teamService.findTeamById(memberAccount.getMember().getTeam().getId());
        model.addAttribute("team", team);
        model.addAttribute("locations", locationService.findAllLocations());
    }

    @PostMapping(path = "modify")
    public String modifyTeam(Model model, TeamRequest request, @RequestParam MultipartFile teamFile, @AuthenticationPrincipal MemberAccount memberAccount) {
        teamService.modifyTeam(memberAccount.getMember().getTeam().getId(), request, teamFile);
        return "redirect:/team/main";
    }

}
