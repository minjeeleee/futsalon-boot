package com.footsalon.inquiry;

import com.footsalon.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    Page<Inquiry> findByMemberGreaterThan(Member member, PageRequest pageRequestIdx);
}
