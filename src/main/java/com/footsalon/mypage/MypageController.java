package com.footsalon.mypage;

import com.footsalon.common.validator.ValidatorResult;
import com.footsalon.member.MemberAccount;
import com.footsalon.member.model.service.MemberService;
import com.footsalon.mypage.validator.ModifyForm;
import com.footsalon.mypage.validator.ModifyFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("mypage")
public class MypageController {

    private final PasswordEncoder passwordEncoder;
    private final ModifyFormValidator modifyFormValidator;
    private final MemberService memberService;

    @InitBinder(value = "modifyForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(modifyFormValidator);
    }

    @GetMapping("leave-id")
    public void leaveId() {}

    @GetMapping("personal-applicant")
    public void personalApplicant() {}

    @GetMapping("alarm")
    public void alarm() {}

    @GetMapping("profile-modify")
    public void profileModify() {}

    @PostMapping("profile-modify")
    public String profileModify(@Validated ModifyForm form
            , Errors errors
            , @RequestParam(required = false) MultipartFile profileImage
            , Model model
            , HttpSession session
            , RedirectAttributes redirectAttr
            , @AuthenticationPrincipal MemberAccount member
    ) {

        ValidatorResult vr = new ValidatorResult();
        model.addAttribute("error", vr.getError());

        if(errors.hasErrors()) {
            vr.addErrors(errors);
            return "mypage/profile";
        }

        memberService.updateMember(form.convertToMember());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails newPrincipal = memberService.loadUserByUsername(member.getUserId());
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, authentication.getCredentials(),newPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return "redirect:/mypage/profile-modify";
    }

    @GetMapping("pw-check")
    @ResponseBody
    public String pwCheck(@AuthenticationPrincipal MemberAccount member, String password) {
        if(passwordEncoder.matches(password,member.getPassword())) {
            return "available";
        }else {
            return "disable";
        }
    }

    @GetMapping("nick-check")
    @ResponseBody
    public String nickCheck(@AuthenticationPrincipal MemberAccount member,String nickName) {
        if(nickName.equals(member.getUserNick()) || !memberService.existsMemberByUserNick(nickName)) {
            return "available";
        }else {
            return "disable";
        }
    }
}
