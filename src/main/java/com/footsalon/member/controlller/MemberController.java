package com.footsalon.member.controlller;

import com.footsalon.member.Member;
import com.footsalon.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("login")
    public void login() {
    }

    @GetMapping("join")
    public void join(@ModelAttribute("member") Member member) {

    }

    @PostMapping("join")
    public String save(@Valid Member member , BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "member/join";
        }

        log.info("member={}",member);

        memberService.join(member);
        return "redirect:/";
    }

}
