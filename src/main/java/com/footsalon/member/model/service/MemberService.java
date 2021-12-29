package com.footsalon.member.model.service;

import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.exception.HandlableException;
import com.footsalon.member.Member;
import com.footsalon.member.MemberAccount;
import com.footsalon.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserIdAndLeaveYn(loginId, "N")
                .orElseThrow(()-> new UsernameNotFoundException(loginId));

        return new MemberAccount(member);
    }

    @Transactional
    public void join(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }


    public boolean existsMemberByUserNick(String nickName) {
        Member member = memberRepository.findByUserNickAndLeaveYn(nickName,"N")
                .orElseThrow(()-> new UsernameNotFoundException(nickName));

        if(member == null){
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public void updateMember(Member convertToMember) {
        Member member = memberRepository.findByUserIdAndLeaveYn(convertToMember.getUserId(),"N").orElseThrow(()-> new HandlableException(ErrorCode.UNAUTHORIZED_PAGE));
        member.setCapacity(convertToMember.getCapacity());
        member.setPassword(passwordEncoder.encode(convertToMember.getPassword()));
        member.setTell(convertToMember.getTell());
        member.setUserNick(convertToMember.getUserNick());
        memberRepository.save(member);
    }
}
