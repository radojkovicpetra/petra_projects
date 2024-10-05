package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PR_movies;

public interface MovieRepo extends JpaRepository<PR_movies, Integer>{
	
}
