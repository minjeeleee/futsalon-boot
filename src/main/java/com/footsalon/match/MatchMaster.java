package com.footsalon.match;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class MatchMaster {

    @Id
    private long mmIdx;
    private String userId;
    private String tmCode;
    private String localCode;
    private String address;
    private LocalDateTime regDate;
    private String title;
    private String expense;
    private String grade;
    private String content;
    private int tmMatch;

    //날짜와 시간을 동시에 받는 방법이 있나?
    private String matchTime;
    //날짜 받는거 방법 고민이 필요함
    private LocalDateTime matchDate;
    private int matchNum;
    private int state;
    private String delYn;

}
