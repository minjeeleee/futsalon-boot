package com.footsalon.notice;

import com.footsalon.member.Member;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Notice {

    @Id
    @GeneratedValue
    private long ntIdx;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Member member;  //작성자

    private String ntTitle;
    private String ntContent;

    @ColumnDefault("sysdate")
    private LocalDate regDate;

    @ColumnDefault("'N'")
    private String delYn;

    @ColumnDefault("1")
    private int ntMain;

    private int views;
}
