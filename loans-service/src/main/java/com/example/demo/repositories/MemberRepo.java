package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PR_member;

public interface MemberRepo extends JpaRepository<PR_member, Integer> {

}
