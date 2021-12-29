package com.footsalon.mypage.validator;

import com.footsalon.member.Member;
import lombok.Data;

@Data
public class ModifyForm {

	private String userId;
	private String memberPass;
	private String newMemberPass;
	private String checkMemberPss;
	private String tell;
	private String nickName;
	private String capacity;

	public Member convertToMember() {
		String newPw = newMemberPass.equals("") ? memberPass : newMemberPass;
		Member member = new Member();
		member.setPassword(newPw);
		member.setTell(tell);
		member.setUserNick(nickName);
		member.setCapacity(capacity);
		return member;
	}
}
