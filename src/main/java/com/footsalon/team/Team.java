package com.footsalon.team;

import com.footsalon.member.Member;

import java.time.LocalDateTime;
import java.util.List;

public class Team {

    private String tmCode;
    private String localCode;
    private String tmName;
    private String tmGrade;
    private String tmInfo;

    private List<Member> memberList;    //멤버
    private Member leader;              //팀장

    private LocalDateTime regDate;
    private LocalDateTime delDate;

}
