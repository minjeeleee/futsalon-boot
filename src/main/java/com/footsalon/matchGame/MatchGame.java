package com.footsalon.matchGame;

import com.footsalon.match.MatchMaster;
import com.footsalon.result.Result;
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

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "thIdx")
    private Result result;

    @OneToMany
    @JoinColumn(name = "userId")
    private List<Member> mercenary = new ArrayList<>();

    private int state; //1:매치성사, 2:결과등록

    /* create */

    public static MatchGame createTeamMatchGame(Team hostTeam, Team awayTeam) {
        return MatchGame.builder()
                .homeTeam(hostTeam)
                .awayTeam(awayTeam)
                .state(1)
                .build();
    }

    public void setMatchMaster(MatchMaster matchMaster) {
        this.matchMaster = matchMaster;
        matchMaster.getMatchGames().add(this);
    }

    public void updateResult(Result result) {
        this.result = result;
        this.state = 2;
    }
}
