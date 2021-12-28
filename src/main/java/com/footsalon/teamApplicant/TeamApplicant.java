package com.footsalon.teamApplicant;

import com.footsalon.member.Member;
import com.footsalon.team.Team;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Entity
public class TeamApplicant {

    @Id @GeneratedValue
    @Column(name = "team_applicant_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime appDate;

    public static TeamApplicant createApplicant(Team team, Member member) {
        return TeamApplicant.builder()
                .team(team)
                .member(member)
                .appDate(LocalDateTime.now())
                .build();
    }


}
