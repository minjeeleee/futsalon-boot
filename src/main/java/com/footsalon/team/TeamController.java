package com.footsalon.team;

import com.footsalon.location.LocationService;
import com.footsalon.match.MatchMasterService;
import com.footsalon.matchGame.MatchGame;
import com.footsalon.matchGame.MatchGameService;
import com.footsalon.member.MemberAccount;
import com.footsalon.member.model.service.MemberService;
import com.footsalon.team.dto.TeamRequest;
import com.footsalon.team.dto.TeamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/team")
public class TeamController {

    private final TeamService teamService;
    private final LocationService locationService;
    private final MemberService memberService;
    private final MatchMasterService matchMasterService;
    private final MatchGameService matchGameService;

    @GetMapping(path = "/main")
    public String teamMain(@AuthenticationPrincipal MemberAccount member) {
        if(member.getGrade().toString().equals("ME00")){ return "team/main"; }
        else { return "redirect:/team/member/modify"; }
    }

    @GetMapping(path = "/create")
    public void createTeam(Model model) {
        model.addAttribute("locations", locationService.findAllLocations());
    }

    @GetMapping(path = "/join")
    public void joinTeam() {
    }

    @GetMapping(path = "/tm-name-check")
    @ResponseBody
    public String tmNameCheck(@RequestParam String tmName) {
        return teamService.tmNameCheck(tmName);
    }

    @GetMapping(path = "/tm-code-check")
    @ResponseBody
    public String tmCodeCheck(@RequestParam String tmCode) {
        return teamService.tmCodeCheck(tmCode);
    }

    @GetMapping(path = "member/modify")
    public void modifyTeam(Model model, @AuthenticationPrincipal MemberAccount memberAccount) {
        model.addAttribute("team", teamService.findTeamWithScoreById(memberAccount.getMember().getTeam().getTmIdx()));
        model.addAttribute("locations", locationService.findAllLocations());
    }

    @GetMapping(path = "member/manage")
    public void manageTeam(Model model, @AuthenticationPrincipal MemberAccount memberAccount) {
        Team team = teamService.findById(memberAccount.getMember().getTeam().getTmIdx());
        model.addAttribute("team", team);
    }

    @GetMapping(path = "member/score")
    public void teamScore(Model model, @AuthenticationPrincipal MemberAccount memberAccount) {
        Team myTeam = teamService.findTeamWithScoreById(memberAccount.getMember().getTeam().getTmIdx());
        List<MatchGame> myMatchGameList = teamService.getMatchGamesInfo(myTeam);
        model.addAttribute("team", myTeam);
        model.addAttribute("matchGameList", myMatchGameList);
    }

    @GetMapping(path = "member/board-team")
    public void teamBoard(Model model, @AuthenticationPrincipal MemberAccount memberAccount) {
        model.addAttribute("teamBoard", matchMasterService.findTeamMatchMastersByTeam(memberAccount.getTeam()));
    }

    @GetMapping(path = "member/board-mercenary")
    public void mercenaryBoard(Model model, @AuthenticationPrincipal MemberAccount memberAccount) {
        model.addAttribute("mercenaryBoard", matchMasterService.findMercenaryMatchMastersByTeam(memberAccount.getTeam()));
    }

    @GetMapping(path = "member/board-team-away")
    public void awayTeamBoard(Model model, @AuthenticationPrincipal MemberAccount memberAccount) {
        model.addAttribute("teamBoard", matchMasterService.findTeamMatchMastersByAwayTeam(memberAccount.getTeam()));
    }

    @GetMapping(path = "member/leave")
    public void leaveTeam() {

    }

    @PostMapping(path = "/create")
    public String createTeam(TeamRequest request,
                             @RequestParam MultipartFile teamFile,
                             @AuthenticationPrincipal MemberAccount memberAccount) {

        teamService.createTeam(memberAccount.getMember(), request, teamFile);
//        Security 설정
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails newPrincipal = memberService.loadUserByUsername(memberAccount.getUserId());
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, authentication.getCredentials(),newPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return "redirect:/team/modify";
    }

    @GetMapping(path = "/join-team")
    public String joinTeam(@RequestParam String tmCode,
                         @AuthenticationPrincipal MemberAccount memberAccount) {
        teamService.joinTeam(tmCode, memberAccount.getMember().getUserId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails newPrincipal = memberService.loadUserByUsername(memberAccount.getUserId());
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, authentication.getCredentials(),newPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return "redirect:/team/modify";
    }

    @PostMapping(path = "/modify")
    public String modifyTeam(Model model, TeamRequest request, @RequestParam MultipartFile teamFile, @AuthenticationPrincipal MemberAccount memberAccount) {
        teamService.modifyTeam(memberAccount.getMember().getTeam().getTmIdx(), request, teamFile);
        return "redirect:/team/main";
    }

    @PostMapping("/team-info")
    @ResponseBody
    public TeamResponse showTeamInfo(Long tmIdx) {
        return teamService.getTeamInfo(tmIdx);
    }

}
