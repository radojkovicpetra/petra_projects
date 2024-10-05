package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the PR_movies database table.
 * 
 */
@Entity
@Table(name="PR_movies")
@NamedQuery(name="PR_movies.findAll", query="SELECT p FROM PR_movies p")
public class PR_movies implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int availability;

	private int duration;

	private String genre;

	@Column(name="publication_year")
	private int publicationYear;

	private String title;

	//bi-directional many-to-one association to PR_loan
	@OneToMany(mappedBy="prmovies")
	private List<PR_loan> prLoans;

	public PR_movies() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvailability() {
		return this.availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getPublicationYear() {
		return this.publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<PR_loan> getPrLoans() {
		return this.prLoans;
	}

	public void setPrLoans(List<PR_loan> prLoans) {
		this.prLoans = prLoans;
	}

	public PR_loan addPrLoan(PR_loan prLoan) {
		getPrLoans().add(prLoan);
		prLoan.setPrmovies(this);

		return prLoan;
	}

	public PR_loan removePrLoan(PR_loan prLoan) {
		getPrLoans().remove(prLoan);
		prLoan.setPrmovies(null);

		return prLoan;
	}

}