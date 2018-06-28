package com.example.himaGatya.Controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.himaGatya.Controller.entity.MemberEntity;



public interface MemberRepository extends JpaRepository<MemberEntity, Long>
{
    public MemberEntity findByUsername(String username);
}