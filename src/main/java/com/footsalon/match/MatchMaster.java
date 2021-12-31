package com.footsalon.match;

import com.footsalon.location.Location;
import com.footsalon.match.dto.TeamMatchRequest;
import com.footsalon.member.Member;
import com.footsalon.team.Team;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Entity
public class MatchMaster {

    @Id
    @GeneratedValue
    private long mmIdx;

    @ManyToOne
    @JoinColumn(name = "tmIdx")
    private Team team;

    private String title;           //타이틀

    @ManyToOne
    @JoinColumn(name = "localCode")
    private Location location;
    private String placeName;
    private String placeAddress;
    private String matchStyle;
    private LocalDateTime matchDateTime;
    private String expense;
    private String teamLevel;
    private String content;

    private int mercenaryCnt;
    private int state;

    private LocalDateTime regDate;
    private String delYn;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matchMaster", fetch = LAZY)
    private List<MatchGame> matchGames = new ArrayList<>();

    public static MatchMaster createMatchMaster(TeamMatchRequest request, Location location, Team team) {
        String matchDateStr = request.getMatchDateTime().replace("T", " ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return MatchMaster.builder()
                .title("[" + location.getLocalCity() + " | " + request.getMatchStyle() + "] " + request.getPlaceName())
                .location(location)
                .team(team)
                .placeName(request.getPlaceName())
                .placeAddress(request.getPlaceAddress())
                .matchStyle(request.getMatchStyle())
                .matchDateTime(LocalDateTime.parse(matchDateStr, formatter))
                .expense(request.getExpense())
                .teamLevel(request.getTeamLevel())
                .content(request.getContent())
                .state(0)
                .delYn("N")
                .regDate(LocalDateTime.now())
                .build();
    }
}
