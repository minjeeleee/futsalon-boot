package com.footsalon.member;

import com.footsalon.common.code.member.MemberGrade;
import com.footsalon.team.Team;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Member {

    @Id
    private String userId;

    @ManyToOne
    @JoinColumn(name = "tmCode")
    private Team team;

    private String password;
    private String userName;

    @Enumerated(EnumType.STRING)
    private MemberGrade grade;

    private String userNick;
    private String email;
    private String tell;
    private String capacity;
    private String leaveYn;
    private LocalDate regDate;



}
