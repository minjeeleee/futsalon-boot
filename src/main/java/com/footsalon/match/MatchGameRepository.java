package com.footsalon.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchGameRepository extends JpaRepository<MatchGame, Long> {

}
