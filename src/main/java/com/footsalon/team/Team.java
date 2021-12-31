package com.footsalon.team;

import com.footsalon.common.util.file.FileInfo;
import com.footsalon.location.Location;
import com.footsalon.member.Member;
import com.footsalon.team.dto.TeamRequest;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
public class Team {

    @Id @GeneratedValue
    private Long tmIdx;

    private String tmCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localCode")
    private Location location;

    @Column(unique = true)
    private String tmName;

    private String tmInfo;

    private String tmLevel;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Member> memberList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flIdx")
    private FileInfo file;

    private LocalDateTime regDate;
    private LocalDateTime delDate;

    /* create */
    public static Team createTeam(TeamRequest request, Location location) {
        return Team.builder()
                .tmCode(UUID.randomUUID().toString().toUpperCase(Locale.ROOT))
                .tmName(request.getTmName())
                .tmLevel(request.getTmLevel())
                .tmInfo(request.getTmInfo())
                .location(location)
                .regDate(LocalDateTime.now())
                .build();
    }

    /* update */
    public void modifyTeam(TeamRequest request, Location location) {
        this.tmLevel = request.getTmLevel();
        this.tmInfo = request.getTmInfo();
        this.location = location;
    }

    /* setter */

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setMemberList(Member member) {
        //this.getMemberList().add(member);
        member.setTeam(this);
    }

    public void setFile(FileInfo file) {
        this.file = file;
    }

    public void setDelDate() {
        this.delDate = LocalDateTime.now();
    }
}
