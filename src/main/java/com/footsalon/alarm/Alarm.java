package com.footsalon.alarm;

import com.footsalon.match.MatchMaster;
import com.footsalon.member.Member;

import java.time.LocalDateTime;

public class Alarm {

    private Long alIdx;
    private Member member;

    //private MatchGame matchGame;

    private MatchMaster matchMaster;

    private Integer state;
    private String content;

    private LocalDateTime alDate;

}
