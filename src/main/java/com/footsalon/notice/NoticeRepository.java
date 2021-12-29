package com.footsalon.notice;

import com.footsalon.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository  extends JpaRepository<Notice, Long> {
}
