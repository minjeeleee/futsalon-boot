package com.footsalon.member.model.service;

import com.footsalon.member.Member;
import com.footsalon.member.MemberAccount;
import com.footsalon.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserIdAndLeaveYn(loginId, "N")
                .orElseThrow(()-> new UsernameNotFoundException(loginId));

        return new MemberAccount(member);
    }

}
