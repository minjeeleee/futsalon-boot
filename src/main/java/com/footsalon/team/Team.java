package com.footsalon.team;

import com.footsalon.location.Location;
import com.footsalon.member.Member;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Team {

    @Id
    private String tmCode;

    @ManyToOne
    @JoinColumn(name = "localCode")
    private Location location;

    private String tmName;
    private String tmGrade;
    private String tmInfo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team", fetch = FetchType.EAGER)
    private List<Member> memberList;    //멤버

    //private Member leader;              //팀장

    private LocalDateTime regDate;
    private LocalDateTime delDate;

}
