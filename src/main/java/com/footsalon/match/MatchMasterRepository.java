package com.footsalon.match;

import com.footsalon.location.Location;
import com.footsalon.team.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchMasterRepository extends JpaRepository<MatchMaster, Long> {

    List<MatchMaster> findByState(int state, Sort regDate);

    List<MatchMaster> findByStateAndMatchDateTimeAfter(int state, LocalDateTime plusHours, Sort regDate);

    List<MatchMaster>  findByStateAndMercenaryCntAndMatchDateTimeAfter(int state, int mercenaryCnt, LocalDateTime plusHours, Sort regDate);

    List<MatchMaster> findTop5ByStateAndMercenaryCntAndMatchDateTimeAfter(int state, int mercenaryCnt, LocalDateTime plusHours, Sort regDate);

    List<MatchMaster> findByTeamAndMercenaryCnt(Team team, int mercenaryCnt, Sort regDate);

    List<MatchMaster> findByTeamAndMercenaryCntNot(Team team, int mercenaryCnt, Sort regDate);

    @Query("select m from MatchMaster m left join fetch MatchGame g on (m.mmIdx = g.matchMaster.mmIdx) where g.awayTeam = :awayTeam and m.mercenaryCnt = :mercenaryCnt order by m.regDate desc ")
    List<MatchMaster> findByAwayTeamAndMercenaryCnt(@Param("awayTeam") Team awayTeam, @Param("mercenaryCnt") int mercenaryCnt, Sort regDate);

    List<MatchMaster> findByStateAndMercenaryCntNotAndMatchDateTimeAfter(int state, int mercenaryCnt, LocalDateTime plusHours, Sort regDate);

//    search

    List<MatchMaster> findByStateAndMercenaryCntAndLocation(int state, int mercenaryCnt, Location location, Sort regDate);

    List<MatchMaster> findByStateAndMercenaryCntAndTeamLevel(int state, int mercenaryCnt, String teamLevel, Sort regDate);

    List<MatchMaster> findByStateAndMercenaryCntAndTeamLevelAndLocation(int state, int mercenaryCnt, String teamLevel, Location location, Sort regDate);

    List<MatchMaster> findByStateAndMercenaryCntNotAndLocation(int state, int mercenaryCnt, Location location, Sort regDate);

    List<MatchMaster> findByStateAndMercenaryCntNotAndTeamLevel(int state, int mercenaryCnt, String teamLevel, Sort regDate);

    List<MatchMaster> findByStateAndMercenaryCntNotAndTeamLevelAndLocation(int state, int mercenaryCnt, String teamLevel, Location location, Sort regDate);

}
