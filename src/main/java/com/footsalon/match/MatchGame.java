package com.footsalon.match;

import com.footsalon.member.Member;
import com.footsalon.team.Team;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
public class MatchGame {

    @Id
    @GeneratedValue
    private Long mgIdx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mmIdx")
    private MatchMaster matchMaster;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "tmIdx")
    private Team team;

    private int state;

    @OneToMany
    @JoinColumn(name = "userId")
    private List<Member> mercenary = new ArrayList<>();

    private String delYn;

    public static MatchGame createTeamMatchGame(MatchMaster matchMaster, Team team) {
        return MatchGame.builder()
                .matchMaster(matchMaster)
                .team(team)
                .state(1)
                .delYn("N")
                .build();
    }
}
