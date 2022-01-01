package com.footsalon.matchGame;

import com.footsalon.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchGameRepository extends JpaRepository<MatchGame, Long> {

    @Query(value = "select g from MatchGame g where g.awayTeam = :awayTeam or g.homeTeam = :homeTeam")
    List<MatchGame> findAllByTmIdx(@Param("awayTeam") Team awayTeam, @Param("homeTeam") Team homeTeam);
}
