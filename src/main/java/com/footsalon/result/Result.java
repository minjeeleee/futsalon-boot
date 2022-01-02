package com.footsalon.result;

import com.footsalon.match.dto.ResultRequest;
import com.footsalon.matchGame.MatchGame;
import com.footsalon.team.Team;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
public class Result {

    @Id
    @GeneratedValue
    private long thIdx;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "winner")
    private Team winner;

    private int homeRating;
    private int awayRating;

    public static Result createResult(Team winner) {
        return Result.builder()
                .winner(winner)
                .build();
    }
}
