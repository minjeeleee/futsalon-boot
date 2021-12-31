package com.footsalon.match;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchMasterRepository extends JpaRepository<MatchMaster, Long> {

    void findByState(int i);

    List<MatchMaster> findByState(int i, Sort sort);
}
