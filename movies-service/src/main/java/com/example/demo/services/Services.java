package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PR_movies;
import com.example.demo.repositories.MovieRepo;

@Service
public class Services {
	@Autowired
	MovieRepo mr;

	public PR_movies vratiFilmPoIdu(int id) {
		return mr.findById(id).get();
	}

	public PR_movies vratiFilmPoNazivu(String naziv) {
		return mr.findByTitle(naziv);
	}

	public PR_movies dodajFilm(PR_movies movie) {
		PR_movies m = new PR_movies();
		m.setTitle(movie.getTitle());
		m.setGenre(movie.getGenre());
		m.setPublicationYear(movie.getPublicationYear());
		return mr.save(m);
	}

	public List<String> vratiListuZanrova() {
		List<PR_movies> filmovi = mr.findAll();
		List<String> zanrs = new ArrayList<>();
		for (PR_movies f : filmovi) {
			zanrs.add(f.getGenre());
		}
		return zanrs;
	}

	public List<PR_movies> vratiFilmovePoZanru(String zanr) {
		List<PR_movies> orig = mr.findAll();
		List<PR_movies> films = new ArrayList<>();
		for (PR_movies m : orig) {
			if (m.getGenre().equalsIgnoreCase(zanr)) {
				films.add(m);
			}
		}
		return films;
	}

	public List<PR_movies> vratiFilmove() {
		return mr.findAll();
	}

	public PR_movies azurirajFilm(PR_movies m) {
		Optional<PR_movies> movieTmp = mr.findById(m.getId());
		if (movieTmp.isPresent()) {
			PR_movies m1 = mr.findByTitle(m.getTitle());
			if (m1 == null) {
				PR_movies a = movieTmp.get();
				a.setTitle(m.getTitle());
				a.setPublicationYear(m.getPublicationYear());
				a.setDuration(m.getDuration());
				a.setGenre(m.getGenre());
				return mr.save(a);
			} else {
				return m1;
			}
		} else {
			PR_movies m1 = mr.findByTitle(m.getTitle());
			if (m1 == null) {
				PR_movies a = new PR_movies();
				a.setTitle(m.getTitle());
				a.setPublicationYear(m.getPublicationYear());
				a.setDuration(m.getDuration());
				a.setGenre(m.getGenre());
				return mr.save(a);
			} else {
				return m1;
			}
		}
	}

	public void deleteMovie(int idM) {
		PR_movies m = mr.findById(idM).get();
		mr.delete(m);
	}

}
