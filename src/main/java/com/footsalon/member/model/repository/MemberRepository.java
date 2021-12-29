package com.footsalon.member.model.repository;

import com.footsalon.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByUserIdAndLeaveYn(String userId,String isLeave);

}