package com.footsalon.teamApplicant;

import com.footsalon.member.Member;
import com.footsalon.team.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamApplicantService {

    private final TeamApplicantRepository teamApplicantRepository;

    @Transactional
    public void createApplicant(Team foundTeam, Member member) {
        teamApplicantRepository.save(TeamApplicant.createApplicant(foundTeam, member));
    }
}
