package com.footsalon.notice;

import com.footsalon.member.Member;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;


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

    @ColumnDefault("0")
    private int ntMain;

    private int views;
}
