package com.footsalon.inquiry;

import com.footsalon.member.Member;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
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

    @ColumnDefault("sysdate")
    private LocalDate regDate;

    @ColumnDefault("'N'")
    private String answerYn;
    private String answer;
}
