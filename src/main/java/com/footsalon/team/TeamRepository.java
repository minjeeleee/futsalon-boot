package com.footsalon.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByTmName(String tmName);

    boolean existsByTmName(String tmName);

    boolean existsByTmCode(String tmCode);

    Team findByTmCode(String tmCode);
}
