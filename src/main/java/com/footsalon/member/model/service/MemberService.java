package com.footsalon.member.model.service;

import com.footsalon.common.code.ErrorCode;
import com.footsalon.common.exception.HandlableException;
import com.footsalon.member.Member;
import com.footsalon.member.MemberAccount;
import com.footsalon.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
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
        return memberRepository.existsByUserNickAndLeaveYn(nickName,"N");
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

    public void leaveMember(Member member) {
        memberRepository.save(member);
    }

    public Member findByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }

    public Member findByUserNameAndEmail(String userName, String email) {
        return memberRepository.findByUserNameAndEmail(userName, email);
    }

    public void sendEmailWithPassword(Member member) {

    }
}
