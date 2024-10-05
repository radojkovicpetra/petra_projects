package com.example.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PR_loan;
import com.example.demo.model.PR_movies;
import com.example.demo.repositories.LoanRepo;
import com.example.demo.repositories.MemberRepo;
import com.example.demo.repositories.MovieRepo;

@Service
public class LoanServices {
	@Autowired
	LoanRepo lr;
	@Autowired
	MovieRepo mr;
	@Autowired
	MemberRepo memr;

	public boolean saveLoan(int idM, int idMem, String loanDate, String returnDate) throws ParseException {
		PR_loan l = new PR_loan();
		l.setLoanDate(new SimpleDateFormat("yyyy-MM-dd").parse(loanDate));
		l.setReturnDate(new SimpleDateFormat("yyyy-MM-dd").parse(returnDate));
		l.setPrMember(memr.findById(idMem).get());
		l.setPrmovies(mr.findById(idM).get());
		PR_movies m = mr.findById(idM).get();
		m.setAvailability(m.getAvailability() - 1);
		mr.save(m);
		return lr.save(l) != null;
	}

	public List<PR_loan> vratiListuZaduzenja() {
		return lr.findAll();
	}

	public void deleteLoan(int idL) {
		PR_loan loan = lr.findById(idL).get();
		PR_movies movies = loan.getPrmovies();
		movies.setAvailability(movies.getAvailability() + 1);
		mr.save(movies);
		lr.delete(loan);
	}

}
