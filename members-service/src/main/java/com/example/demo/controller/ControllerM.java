package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PR_member;
import com.example.demo.service.Services;

@RestController
public class ControllerM {
	@Autowired
	Services s;
	
	@Autowired
	Environment env;
	
	@GetMapping("/members")
	public List<PR_member> getMembers() {
		return s.vratiListuKorisnika();
	}
	@GetMapping("/getMemberById/{id}")
	public PR_member getMById(@PathVariable int id) {
		return s.findId(id);
	}
	@PostMapping("/addMember")
	public ResponseEntity<PR_member> saveMember(@RequestBody PR_member member){
		PR_member m = s.dodajKorisnika(member);
		return new ResponseEntity<PR_member>(m, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateMember")
	public PR_member updateMember(@RequestBody PR_member member) {
		return s.azurirajKorisnika(member);
	}
	@DeleteMapping("/deleteMember/{idM}")
	public void deleteMember(@PathVariable int idM) throws NotFoundException {
		if (s.findId(idM) == null) {
			throw new NotFoundException();
		}
		else {
			s.deleteMember(idM);
		}
	}
	@GetMapping("/getPortMem")
	public String getPort() {
		return env.getProperty("local.server.port");
	}
	
}
