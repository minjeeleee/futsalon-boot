package com.footsalon.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mypage")
public class MypageController {

    @GetMapping("leave-id")
    public void leaveId() {}

    @GetMapping("profile-modify")
    public void profileModify() {}

    @GetMapping("personal-applicant")
    public void personalApplicant() {}

    @GetMapping("alarm")
    public void alarm() {}
}
