package com.example.demo.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PR_loan;
import com.example.demo.services.LoanServices;

@RestController
public class ControllerLoan {
	@Autowired
	LoanServices s;
	
	@Autowired
	Environment env;
	
	@PostMapping("/addLoan/{idM}/{idMem}/{loanDate}/{returnDate}")
	public boolean saveLoan(@PathVariable int idM, @PathVariable int idMem, @PathVariable String loanDate,
			@PathVariable String returnDate) throws ParseException {
		return s.saveLoan(idM, idMem, loanDate, returnDate);
	}

	@GetMapping("/loans")
	public List<PR_loan> getLoans() {
		return s.vratiListuZaduzenja();
	}

	@DeleteMapping("/deleteLoan/{idL}")
	public void deleteLoan(@PathVariable int idL) {
		s.deleteLoan(idL);
	}
	@GetMapping("/getPortL")
	public String getPort() {
		return env.getProperty("local.server.port");
	}
}
