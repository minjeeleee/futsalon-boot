package com.footsalon.member;

import com.footsalon.common.code.member.MemberGrade;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.List;

public class MemberAccount extends User {

    private Member member;

    public MemberAccount(Member member) {
        super(member.getUserId(), member.getPassword(),List.of(new SimpleGrantedAuthority(member.getGrade().DESC)));
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
    public String getPassword(){
        return member.getPassword();
    }
    public String getUserName(){
        return member.getUserName();
    }
    public MemberGrade getGrade(){
        return member.getGrade();
    }
    public String getUserNick(){
        return member.getUserNick();
    }
    public String getEmail(){
        return member.getPassword();
    }
    public String getTell(){
        return member.getPassword();
    }
    public String getCapacity(){
        return member.getPassword();
    }
    public String getLeaveYn(){
        return member.getPassword();
    }
    public LocalDate getRegDate(){
        return member.getRegDate();
    }


}
