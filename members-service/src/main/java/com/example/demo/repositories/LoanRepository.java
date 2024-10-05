package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PR_loan;
import com.example.demo.model.PR_member;

public interface LoanRepository extends JpaRepository<PR_loan, Integer>{
	public List<PR_loan> findByPrMember(PR_member member);
}
