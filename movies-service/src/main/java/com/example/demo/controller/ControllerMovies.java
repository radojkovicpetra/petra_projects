package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PR_movies;
import com.example.demo.services.Services;

@RestController
public class ControllerMovies {
	@Autowired
	Services s;
	
	@Autowired
	Environment env;
	
	@GetMapping("/getMovieById/{id}")
	public PR_movies getMBId(@PathVariable int id) {
		return s.vratiFilmPoIdu(id);
	}
	@GetMapping("/vratiFilmPoNazivu/{naziv}")
	public PR_movies getMBTitle(@PathVariable String naziv) {
		return s.vratiFilmPoNazivu(naziv);
	}
	@GetMapping("/movies")
	public List<PR_movies> vratiFilmove(){
		return s.vratiFilmove();
	}
	@GetMapping("/genres")
	public List<String> vratiListuZanrova(){
		return s.vratiListuZanrova();
	}
	@GetMapping("/getmoviesByGenre/{naziv}")
	public List<PR_movies> getMBGenres(@PathVariable String naziv){
		return s.vratiFilmovePoZanru(naziv);
	}
	@PostMapping("/addMovie")
	public ResponseEntity<PR_movies> saveMovie(@RequestBody PR_movies movie){
		PR_movies m = s.dodajFilm(movie);
		return new ResponseEntity<PR_movies>(m, HttpStatus.CREATED);
	}
	@PutMapping("/updateMovie")
	public PR_movies updateMovie(@RequestBody PR_movies movie) {
		return s.azurirajFilm(movie);
	}
	@DeleteMapping("/deleteMovie/{idM}")
	public void deleteMovie(@PathVariable int idM) {
		s.deleteMovie(idM);
	}
	@GetMapping("/getPortM")
	public String getPort() {
		return env.getProperty("local.server.port");
	}
}
