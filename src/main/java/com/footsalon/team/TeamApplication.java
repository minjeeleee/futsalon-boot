package com.footsalon.team;

import com.footsalon.member.Member;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class TeamApplication {

    @Id @GeneratedValue
    private Long taIdx;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tm_idx")
    private Team team;
}
