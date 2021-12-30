package com.footsalon.inquiry;

import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.exception.HandlableException;
import com.footsalon.common.util.pagination.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    public Map<String,Object> findInquirysByPage(int pageNumber) {

        int cntPerPage = 5;

        Page<Inquiry> page = inquiryRepository.findAll(PageRequest.of(pageNumber-1, cntPerPage, Direction.DESC, "iqIdx"));
        List<Inquiry> inquirys = page.getContent();
        Paging pageUtil = Paging.builder()
                .url("/inquiry/inquiry")
                .total((int)inquiryRepository.count())
                .cntPerPage(cntPerPage)
                .blockCnt(10)
                .curPage(pageNumber)
                .build();

        return Map.of("inquiryList", inquirys, "paging", pageUtil);
    }

    public void persistInquiry(Inquiry inquiry) {
        inquiryRepository.save(inquiry);
    }

    public Inquiry findInquiryByIdx(long iqIdx) {
        return inquiryRepository.findById(iqIdx)
                .orElseThrow(()-> new HandlableException(ErrorCode.UNAUTHORIZED_PAGE));
    }

    public void modifyInquiry(Inquiry inquiry) {
        inquiryRepository.save(inquiry);
    }

    public void deleteInquiry(long iqIdx) {
        inquiryRepository.deleteById(iqIdx);
    }
}
