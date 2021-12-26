package com.footsalon.inquiry;

import com.footsalon.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Inquiry {

    @Id
    @GeneratedValue
    private Long iqIdx;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Member member;  //작성자

    private String title;
    private String content;
    private String type;

    private LocalDateTime regDate;
    private LocalDateTime delDate;

    @Column(columnDefinition = "char default 'N'")
    private String answerYn;
    private String answer;
}
