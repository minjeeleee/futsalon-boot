package com.footsalon.team;

import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.code.member.MemberGrade;
import com.footsalon.common.exception.HandlableException;
import com.footsalon.common.util.file.FileInfo;
import com.footsalon.common.util.file.FileUtil;
import com.footsalon.location.Location;
import com.footsalon.location.LocationService;
import com.footsalon.member.Member;
import com.footsalon.member.MemberAccount;
import com.footsalon.member.model.service.MemberService;
import com.footsalon.team.dto.TeamRequest;
import com.footsalon.teamApplicant.TeamApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;
    private final LocationService locationService;
    private final TeamApplicantService teamApplicantService;
    private final MemberService memberService;

    @Transactional
    public void createTeam(Member member, TeamRequest request, MultipartFile teamFile) {
        Member findMember = memberService.findByUserId(member.getUserId());
        Location location = locationService.findLocation(request.getLocalCode());
        Team team = Team.createTeam(request, location);
        team.setMemberList(findMember);

        if(!teamFile.isEmpty()) {
            FileUtil fileUtil = new FileUtil();
            FileInfo fileInfo = fileUtil.fileUpload(teamFile);
            team.setFile(fileInfo);
        }

        teamRepository.save(team);
//        회원등급 - 리더로 변경
        findMember.setGrade(MemberGrade.ME03);
    }

    public String tmNameCheck(String tmName) {
        if(teamRepository.existsByTmName(tmName)) {
            return "disable";
        }
        return "available";
    }

    public Team findTeamById(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(()->new HandlableException(ErrorCode.TEAM_DOES_NOT_EXIST));
        return team;
    }

    @Transactional
    public void modifyTeam(Long teamId, TeamRequest request, MultipartFile teamFile) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new HandlableException(ErrorCode.TEAM_DOES_NOT_EXIST));
        Location location = locationService.findLocation(request.getLocalCode());
        team.modifyTeam(request, location);
        System.out.println("request = " + request.getTmGrade());
        System.out.println("team = " + team.getTmGrade());

        if(!teamFile.isEmpty()) {
            FileUtil fileUtil = new FileUtil();
            FileInfo fileInfo = fileUtil.fileUpload(teamFile);
            team.setFile(fileInfo);
        }
    }

    @Transactional
    public void leaveTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new HandlableException(ErrorCode.TEAM_DOES_NOT_EXIST));
        team.setDelDate();
    }

}
