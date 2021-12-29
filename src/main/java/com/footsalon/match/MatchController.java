package com.footsalon.match;

import com.footsalon.location.Location;
import com.footsalon.location.LocationService;
import com.footsalon.match.dto.TeamMatchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/match")
public class MatchController {

    private final LocationService locationService;

    @GetMapping(path = "/team-list")
    public void teamList(Model model) {
        model.addAttribute("locations", locationService.findAllLocations());
    }

    @GetMapping(path = "/team-match-form")
    public void teamMatchForm(Model model) {
        model.addAttribute("locations", locationService.findAllLocations());
    }

    @PostMapping(path = "/create-team-match")
    public String createMatchMaster(TeamMatchRequest request) {
        System.out.println("request = " + request);
        return "redirect:/match/team-list";
    }

}
