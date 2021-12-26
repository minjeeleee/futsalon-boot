package com.footsalon.inquiry;

import com.footsalon.member.Member;

import java.time.LocalDateTime;

public class Inquiry {

    private Long iqIdx;
    private Member member;  //작성자
    private String title;
    private String content;
    private String type;

    private LocalDateTime regDate;
    private LocalDateTime delDate;

    private String answerYn;
    private String answer;
}
