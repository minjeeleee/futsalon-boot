package com.footsalon.notice;

import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.exception.HandlableException;
import com.footsalon.common.util.pagination.Paging;
import com.footsalon.inquiry.Inquiry;
import com.footsalon.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    public Map<String,Object> findNoticesByPage(int pageNumber) {

        int cntPerPage = 5;

        Page<Notice> page = noticeRepository.findAll(PageRequest.of(pageNumber-1, cntPerPage, Direction.DESC, "ntIdx","ntMain"));
        List<Notice> notices = page.getContent();

        Paging pageUtil = Paging.builder()
                .url("/notice/notice-list")
                .total((int)noticeRepository.count())
                .cntPerPage(cntPerPage)
                .blockCnt(10)
                .curPage(pageNumber)
                .build();
        return Map.of("noticeList", notices, "paging", pageUtil);
    }

    public void persistNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    public Notice findNoticeByIdx(long ntIdx) {
        Notice notice = noticeRepository.findById(ntIdx).orElseThrow(()-> new HandlableException(ErrorCode.UNAUTHORIZED_PAGE));
        notice.setViews(notice.getViews()+1);
        noticeRepository.save(notice);

        return noticeRepository.findById(ntIdx)
                .orElseThrow(()-> new HandlableException(ErrorCode.UNAUTHORIZED_PAGE));
    }

    public void modifyNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    public void deleteNotice(long ntIdx) {
        Notice notice = noticeRepository.findById(ntIdx).orElseThrow(()-> new HandlableException(ErrorCode.UNAUTHORIZED_PAGE));
        notice.setDelYn("Y");
        noticeRepository.save(notice);
    }

    public void updateNotice(long ntIdx) {
        Notice notice = noticeRepository.findById(ntIdx).orElseThrow(()-> new HandlableException(ErrorCode.UNAUTHORIZED_PAGE));
        notice.setNtMain(0);
        noticeRepository.save(notice);
    }
}
