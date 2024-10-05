package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The persistent class for the PR_members database table.
 * 
 */
@Entity
@Table(name = "PR_members")
@NamedQuery(name = "PR_member.findAll", query = "SELECT p FROM PR_member p")
public class PR_member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "contact_info")

	private String contactInfo;

	@Column(name = "first_name")

	private String firstName;

	@Column(name = "last_name")

	private String lastName;

	// bi-directional many-to-one association to PR_loan
	@JsonIgnore
	@OneToMany(mappedBy = "prMember")
	private List<PR_loan> prLoans;

	public PR_member() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContactInfo() {
		return this.contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<PR_loan> getPrLoans() {
		return this.prLoans;
	}

	public void setPrLoans(List<PR_loan> prLoans) {
		this.prLoans = prLoans;
	}

	public PR_loan addPrLoan(PR_loan prLoan) {
		getPrLoans().add(prLoan);
		prLoan.setPrMember(this);

		return prLoan;
	}

	public PR_loan removePrLoan(PR_loan prLoan) {
		getPrLoans().remove(prLoan);
		prLoan.setPrMember(null);

		return prLoan;
	}

}