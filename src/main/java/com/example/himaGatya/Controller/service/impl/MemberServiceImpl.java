package com.example.himaGatya.Controller.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.himaGatya.Controller.entity.MemberEntity;
import com.example.himaGatya.Controller.repository.MemberRepository;

@Service
public class MemberServiceImpl implements UserDetailsService
{
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        if (StringUtils.isEmpty(username))
        {
            throw new UsernameNotFoundException("");
        }

        MemberEntity memberEntity = memberRepository.findByUsername(username);
        if (memberEntity == null)
        {
            throw new UsernameNotFoundException("");
        }

        return memberEntity;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }
}
