package com.footsalon.match;

import com.footsalon.team.Team;
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

    List<MatchMaster> findByStateAndMatchDateTimeAfter(int i, LocalDateTime plusHours, Sort regDate);

    List<MatchMaster> findByTeam(Team team, Sort regDate);

    @Query("select m from MatchMaster m left join fetch MatchGame g on (m.mmIdx = g.matchMaster.mmIdx) where g.awayTeam = :awayTeam order by m.regDate desc ")
    List<MatchMaster> findByAwayTeam(@Param("awayTeam") Team awayTeam);
}
