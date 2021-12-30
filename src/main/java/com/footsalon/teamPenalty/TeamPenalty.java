package com.footsalon.teamPenalty;

import com.footsalon.member.Member;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class TeamPenalty {

    @Id
    @GeneratedValue
    private Long tpIdx;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Member member;
    private LocalDateTime delDate;
}
