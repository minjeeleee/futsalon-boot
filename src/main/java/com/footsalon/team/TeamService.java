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
            return "exists";
        }
        return "notExist";
    }

    public String tmCodeCheck(String tmCode) {
        if(teamRepository.existsByTmCode(tmCode)) {
            return "exists";
        }
        return "notExist";
    }

    public Team findTeamById(Long teamIdx) {
        return teamRepository.findById(teamIdx).orElseThrow(()->new HandlableException(ErrorCode.TEAM_DOES_NOT_EXIST));
    }

    @Transactional
    public void modifyTeam(Long teamIdx, TeamRequest request, MultipartFile teamFile) {
        Team team = teamRepository.findById(teamIdx).orElseThrow(() -> new HandlableException(ErrorCode.TEAM_DOES_NOT_EXIST));
        Location location = locationService.findLocation(request.getLocalCode());
        team.modifyTeam(request, location);

        if(!teamFile.isEmpty()) {
            FileUtil fileUtil = new FileUtil();
            FileInfo fileInfo = fileUtil.fileUpload(teamFile);
            team.setFile(fileInfo);
        }
    }

    @Transactional
    public void leaveTeam(Long teamIdx) {
        Team team = teamRepository.findById(teamIdx).orElseThrow(() -> new HandlableException(ErrorCode.TEAM_DOES_NOT_EXIST));
        team.setDelDate();
    }

    @Transactional
    public void joinTeam(String tmCode, String userId) {
        System.out.println("===");
        Member member = memberService.findByUserId(userId);
        System.out.println("member.getUserId() = " + member.getUserId());
        Team team = teamRepository.findByTmCode(tmCode);
        System.out.println("team.getTmName() = " + team.getTmName());
        member.setTeam(team);
        member.setGrade(MemberGrade.ME01);
    }
}
