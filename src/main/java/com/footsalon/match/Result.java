package com.footsalon.match;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Result {

    @Id
    @GeneratedValue
    private long thIdx;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mgIdx")
    private MatchGame matchGame;

    @Column(columnDefinition = "number default 0")
    private int rivalRating;
    @Column(columnDefinition = "number default 0")
    private int hostRating;

    private String winner;
}
