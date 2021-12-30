package com.footsalon.mypage.validator;

import java.util.regex.Pattern;

import com.footsalon.member.Member;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ModifyFormValidator implements Validator{


	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ModifyForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ModifyForm form = (ModifyForm) target;

		if(! Pattern.matches(".*[0-9a-zA-Z가-힣]{2,}", form.getNickName())) {
			errors.rejectValue("nickName", "error-memberNick", "닉네임은 2글자 이상의 숫자 또는 한글 또는 영문 조합 입니다.");
		}
	  
	    if(!Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,}",form.getNewMemberPass()) && !form.getNewMemberPass().equals("")) { 
	    	errors.rejectValue("newMemberPass","err-newMemberPass", "비밀번호는 숫자 영문자 특수문자 조합인 8글자 이상의 문자열입니다."); 
	    }
	  
	    if(!form.getNewMemberPass().equals(form.getCheckMemberPss())) {
	    	errors.rejectValue("checkMemberPss", "err-checkMemberPss","비밀번호가 일치하지 않습니다.");
	    }
	  
	    if(!Pattern.matches("\\d{9,11}", form.getTell())) {
	    	errors.rejectValue("tell", "err-memberTell", "전화번호는 9~11자리의 숫자입니다.");
	    }
	 

	}
	
}
