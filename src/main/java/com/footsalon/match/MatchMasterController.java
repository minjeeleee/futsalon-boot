package com.footsalon.match;

import com.footsalon.location.LocationService;
import com.footsalon.match.dto.TeamMatchRequest;
import com.footsalon.member.MemberAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/match")
public class MatchMasterController {

    private final LocationService locationService;
    private final MatchMasterService matchMasterService;

    @GetMapping(path = "/team-list")
    public void teamList(Model model) {
        model.addAttribute("locations", locationService.findAllLocations());
        List<MatchMaster> ingMatchs = matchMasterService.findIngMatchs();

    }

    @GetMapping(path = "/team-match-form")
    public void teamMatchForm(Model model) {
        model.addAttribute("locations", locationService.findAllLocations());
    }

    @PostMapping(path = "/create-team-match")
    public String createMatchMaster(TeamMatchRequest request, @AuthenticationPrincipal MemberAccount memberAccount) {
        matchMasterService.createMatchMaster(request, memberAccount.getMember().getTeam().getTmIdx());
        return "redirect:/match/team-list";
    }

}
