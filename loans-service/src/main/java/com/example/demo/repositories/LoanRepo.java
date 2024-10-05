package com.example.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PR_loan;

public interface LoanRepo extends JpaRepository<PR_loan, Integer> {
}
