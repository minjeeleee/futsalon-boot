package com.footsalon.alarm;

import com.footsalon.match.MatchGame;
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
public class Alarm {

    @Id
    @GeneratedValue
    private Long alIdx;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "mgIdx")
    private MatchGame matchGame;

    private Integer state;
    private String content;

    private LocalDateTime alDate;

    private String matchTime;
    private LocalDateTime ntDate;

}
