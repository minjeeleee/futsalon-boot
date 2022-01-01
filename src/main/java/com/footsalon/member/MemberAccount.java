package com.footsalon.member;

import com.footsalon.common.code.member.MemberGrade;
import com.footsalon.team.Team;
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
    public String getUserId(){
        return member.getUserId();
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
        return member.getEmail();
    }
    public String getTell(){
        return member.getTell();
    }
    public String getCapacity(){
        return member.getCapacity();
    }
    public String getLeaveYn(){
        return member.getLeaveYn();
    }
    public LocalDate getRegDate(){
        return member.getRegDate();
    }

    public Team getTeam() {
        return member.getTeam();
    }

}
