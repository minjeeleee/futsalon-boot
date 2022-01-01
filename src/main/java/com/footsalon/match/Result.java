package com.footsalon.match;

import com.footsalon.matchGame.MatchGame;
import com.footsalon.team.Team;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
public class Result {

    @Id
    @GeneratedValue
    private long thIdx;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "mgIdx")
    private MatchGame matchGame;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "tmIdx")
    private Team team;

    private int teamRating;

    private String result;
}
