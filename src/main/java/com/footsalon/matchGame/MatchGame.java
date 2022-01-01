package com.footsalon.matchGame;

import com.footsalon.match.MatchMaster;
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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "homeTeam")
    private Team homeTeam;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "awayTeam")
    private Team awayTeam;

    @OneToMany
    @JoinColumn(name = "userId")
    private List<Member> mercenary = new ArrayList<>();

    private int state;

    public static MatchGame createTeamMatchGame(MatchMaster matchMaster, Team awayTeam) {
        return MatchGame.builder()
                .matchMaster(matchMaster)
                .homeTeam(matchMaster.getTeam())
                .awayTeam(awayTeam)
                .state(1)
                .build();
    }
}
