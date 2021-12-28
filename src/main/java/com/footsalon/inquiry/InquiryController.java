package com.footsalon.inquiry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("inquiry")
public class InquiryController {

    @GetMapping("inquiry")
    public void inquiry() {}

    @GetMapping("inquiry-form")
    public void inquiryForm() {}

    @GetMapping("inquiry-detail")
    public void inquiryDetail() {}

}
