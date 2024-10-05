package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the PR_loans database table.
 * 
 */
@Entity
@Table(name="PR_loans")
@NamedQuery(name="PR_loan.findAll", query="SELECT p FROM PR_loan p")
public class PR_loan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="loan_date")
	private Date loanDate;

	@Temporal(TemporalType.DATE)
	@Column(name="return_date")
	private Date returnDate;

	//bi-directional many-to-one association to PR_movies
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="movie_id")
	private PR_movies prmovies;

	//bi-directional many-to-one association to PR_member
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="member_id")
	
	private PR_member prMember;

	public PR_loan() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLoanDate() {
		return this.loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public PR_movies getPrmovies() {
		return this.prmovies;
	}

	public void setPrmovies(PR_movies prmovies) {
		this.prmovies = prmovies;
	}

	public PR_member getPrMember() {
		return this.prMember;
	}

	public void setPrMember(PR_member prMember) {
		this.prMember = prMember;
	}

}