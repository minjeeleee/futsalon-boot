package com.footsalon.match;

import com.footsalon.location.Location;
import com.footsalon.member.Member;
import com.footsalon.team.Team;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class MatchMaster {

    @Id
    @GeneratedValue
    private long mmIdx;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "tmCode")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "localCode")
    private Location location;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matchMaster", fetch = FetchType.EAGER)
    private List<MatchGame> matchGames = new ArrayList<MatchGame>();//ToMany관계일 경우 필드를 초기화 해둘 것

}
