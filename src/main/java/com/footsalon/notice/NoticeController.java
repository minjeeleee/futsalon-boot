package com.footsalon.notice;

import com.footsalon.inquiry.Inquiry;
import com.footsalon.member.MemberAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("notice-list")
    public void noticeList(@AuthenticationPrincipal MemberAccount memer, Model model, @RequestParam(required = false, defaultValue = "1") int page) {
        model.addAttribute(noticeService.findNoticesByPage(page,memer.getUserId()));
    }

    @GetMapping("notice-form")
    public void noticeForm() {}

    @PostMapping("upload")
    public String upload(@AuthenticationPrincipal MemberAccount memberAccount, Notice notice, Model model) {
        notice.setMember(memberAccount.getMember());
        noticeService.persistNotice(notice);
        model.addAttribute("msg","공지사항 등록이 완료되었습니다.");
        model.addAttribute("url","/notice/notice-list");
        return "common/result";
    }

    @GetMapping("notice-detail")
    public void inquiryDetail(long ntIdx,Model model) {
        Notice notice = noticeService.findNoticeByIdx(ntIdx);
        model.addAttribute("notice", notice);
    }

    @PostMapping("modify")
    public String modify(Notice notice) {
        noticeService.modifyNotice(notice);
        return "redirect:/notice/notice-detail?ntIdx="+notice.getNtIdx();
    }

    @GetMapping ("main")
    public String main(Model model,long ntIdx) {
        noticeService.updateNotice(ntIdx);
        model.addAttribute("msg","메인 등록이 완료되었습니다.");
        model.addAttribute("url","/notice/notice-list");
        return "common/result";
    }
    

    @GetMapping ("delete")
    public String delete(Model model,long ntIdx) {
        noticeService.deleteNotice(ntIdx);
        model.addAttribute("msg","공지사항 삭제가 완료되었습니다.");
        model.addAttribute("url","/notice/notice-list");
        return "common/result";
    }

}
