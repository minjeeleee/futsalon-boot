package com.footsalon.match;

import com.footsalon.team.Team;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchMasterRepository extends JpaRepository<MatchMaster, Long> {

    List<MatchMaster> findByState(int state, Sort regDate);

    List<MatchMaster> findByStateAndMatchDateTimeAfter(int i, LocalDateTime plusHours, Sort regDate);

    List<MatchMaster> findByTeam(Team team);
}
