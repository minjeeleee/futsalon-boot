package com.footsalon.match;

import com.footsalon.location.LocationService;
import com.footsalon.match.dto.*;
import com.footsalon.member.MemberAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/match")
public class MatchMasterController {

    private final LocationService locationService;
    private final MatchMasterService matchMasterService;

    @GetMapping(path = "/team-list")
    public void teamList(Model model, @RequestParam @Nullable String sort) {
        if (sort == null) sort = "";
        model.addAttribute("matchBoardList", matchMasterService.findMatchBoardList(sort));
        model.addAttribute("locations", locationService.findAllLocations());
    }

    @GetMapping(path = "/team-match-form")
    public void teamMatchForm(Model model, @Nullable Long mmIdx) {
        if (mmIdx != null) {
            model.addAttribute("matchBoard", matchMasterService.findMatchMasterByMmIdx(mmIdx));
        }
        model.addAttribute("locations", locationService.findAllLocations());
    }

    @PostMapping(path = "/create-team-match")
    public String createTeamMatchMaster(TeamMatchRequest request, @AuthenticationPrincipal MemberAccount memberAccount) {
        matchMasterService.createTeamMatchMaster(request, memberAccount.getTeam().getTmIdx());
        return "redirect:/match/team-list";
    }

    @PostMapping(path = "/update-team-match")
    public String updateTeamMatchMaster(TeamMatchRequest request, Long mmIdx, @AuthenticationPrincipal MemberAccount memberAccount) {
        matchMasterService.updateTeamMatchMaster(request, memberAccount.getTeam().getTmIdx(), mmIdx);
        return "redirect:/match/team-list";
    }

    @GetMapping(path = "/mercenary-list")
    public void mercenaryList(Model model, @RequestParam @Nullable String sort) {
        if (sort == null) sort = "";
        model.addAttribute("matchBoardList", matchMasterService.findMercenaryMatchBoardList(sort));
        model.addAttribute("locations", locationService.findAllLocations());
    }

    @GetMapping(path = "/mercenary-match-form")
    public void mercenaryMatchForm(Model model) {
        model.addAttribute("locations", locationService.findAllLocations());
    }

    @PostMapping(path = "/create-mercenary-match")
    public String createMercenaryMatchMaster(@Valid MercenaryMatchRequest request, @AuthenticationPrincipal MemberAccount memberAccount) {
        matchMasterService.createMercenaryMatchMaster(request, memberAccount.getTeam().getTmIdx());
        return "redirect:/match/mercenary-list";
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

    @PostMapping("/team-match-search")
    public String teamMatchcSearch(Model model, SearchRequest request) {
        model.addAttribute("matchBoardList", matchMasterService.searchTeamMatchMaster(request));
        model.addAttribute("locations", locationService.findAllLocations());
        String searchType = "";
        if (request.getLocalCode() != null) {
            searchType += "????????????";
        }
        if (request.getLevel() != null) {
            if (request.getLocalCode() != null) searchType += ", ";
            searchType += "????????????" ;
        }
        model.addAttribute("searchType", searchType);
        return "match/team-list";
    }

    @PostMapping("/mercenary-match-search")
    public String mercenaryMatchcSearch(Model model, SearchRequest request) {
        model.addAttribute("matchBoardList", matchMasterService.searchMercenaryMatchMaster(request));
        model.addAttribute("locations", locationService.findAllLocations());
        String searchType = "";
        if (request.getLocalCode() != null) {
            searchType += "????????????";
        }
        if (request.getLevel() != null) {
            if (request.getLocalCode() != null) searchType += ", ";
            searchType += "????????????" ;
        }
        model.addAttribute("searchType", searchType);
        return "match/mercenary-list";
    }
}
