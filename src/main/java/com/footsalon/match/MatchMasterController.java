package com.footsalon.match;

import com.footsalon.location.LocationService;
import com.footsalon.match.dto.RatingRequest;
import com.footsalon.match.dto.ResultRequest;
import com.footsalon.match.dto.TeamMatchRequest;
import com.footsalon.member.MemberAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/match")
public class MatchMasterController {

    private final LocationService locationService;
    private final MatchMasterService matchMasterService;

    @GetMapping(path = "/team-list")
    public void teamList(Model model) {
        model.addAttribute("matchBoardList", matchMasterService.findMatchBoardList());
        model.addAttribute("locations", locationService.findAllLocations());
    }

    @GetMapping(path = "/team-match-form")
    public void teamMatchForm(Model model) {
        model.addAttribute("locations", locationService.findAllLocations());
    }

    @PostMapping(path = "/create-team-match")
    public String createMatchMaster(TeamMatchRequest request, @AuthenticationPrincipal MemberAccount memberAccount) {
        matchMasterService.createMatchMaster(request, memberAccount.getTeam().getTmIdx());
        return "redirect:/match/team-list";
    }

    @PostMapping(path = "/apply-team-match")
    @ResponseBody
    public String applyTeamMatch(Long mmIdx, @AuthenticationPrincipal MemberAccount memberAccount) {
        return matchMasterService.applyTeamMatch(mmIdx, memberAccount.getTeam().getTmIdx());
    }

    @PostMapping(path = "/save-result")
    @ResponseBody
    public String saveResult(ResultRequest request) {
        matchMasterService.saveResult(request);
        return "success";
    }

    @PostMapping(path = "/save-rating")
    @ResponseBody
    public String saveRating(RatingRequest request) {
        matchMasterService.saveRating(request);
        return "success";

    }
}
