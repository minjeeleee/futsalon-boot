package com.footsalon.member;

import com.footsalon.common.code.member.MemberGrade;
import com.footsalon.team.Team;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Member {

    @Id
    @NotEmpty
    private String userId;

    @ManyToOne
    @JoinColumn(name = "team_idx")
    private Team team;

    @NotEmpty
    private String password;
    @NotEmpty
    private String userName;

    @ColumnDefault("'ME00'")
    @Enumerated(EnumType.STRING)
    private MemberGrade grade;

    @NotEmpty
    private String userNick;
    @NotEmpty
    private String email;
    @NotEmpty
    private String tell;
    @NotEmpty
    private String capacity;
    @ColumnDefault("'N'")
    private String leaveYn;
    @ColumnDefault("sysdate")
    private LocalDate regDate;



}
