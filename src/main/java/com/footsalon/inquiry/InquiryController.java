package com.footsalon.inquiry;

import com.footsalon.member.MemberAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("inquiry")
public class InquiryController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final InquiryService inquiryService;

    @GetMapping("inquiry")
    public void inquiry(@AuthenticationPrincipal MemberAccount memer,Model model, @RequestParam(required = false, defaultValue = "1") int page) {
        model.addAttribute(inquiryService.findInquirysByPage(page,memer.getUserId()));
    }

    @GetMapping("inquiry-form")
    public void inquiryForm() {}

    @PostMapping("upload")
    public String upload(@AuthenticationPrincipal MemberAccount memberAccount, Inquiry inquiry,Model model) {
        inquiry.setMember(memberAccount.getMember());
        inquiryService.persistInquiry(inquiry);
        model.addAttribute("msg","문의사항 등록이 완료되었습니다.");
        model.addAttribute("url","/inquiry/inquiry");
        return "common/result";
    }

    @GetMapping("inquiry-detail")
    public void inquiryDetail(long iqIdx,Model model) {
        Inquiry inquiry = inquiryService.findInquiryByIdx(iqIdx);
        model.addAttribute("inquiry", inquiry);
    }

    @PostMapping("modify")
    public String modify(Inquiry inquiry) {
        inquiryService.modifyInquiry(inquiry);
        return "redirect:/inquiry/inquiry-detail?iqIdx="+inquiry.getIqIdx();
    }

    @GetMapping ("delete")
    public String delete(Model model,long iqIdx) {
        inquiryService.deleteInquiry(iqIdx);
        model.addAttribute("msg","문의사항 삭제가 완료되었습니다.");
        model.addAttribute("url","/inquiry/inquiry");
        return "common/result";
    }

}
