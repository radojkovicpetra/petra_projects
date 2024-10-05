package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PR_loan;
import com.example.demo.model.PR_member;
import com.example.demo.repositories.LoanRepository;
import com.example.demo.repositories.MemberRepo;

@Service
public class Services {
	@Autowired
	MemberRepo mr;
	@Autowired
	LoanRepository lr;
	
	public List<PR_member> vratiListuKorisnika() {
		return mr.findAll();
	}
	
	public PR_member dodajKorisnika(PR_member member) {
		PR_member a = new PR_member();
		try {
			a.setFirstName(member.getFirstName());
			a.setLastName(member.getLastName());
			a.setContactInfo(member.getContactInfo());
			mr.save(a);

		} catch (Exception ex) {
			ex.getMessage();
		}
		return a;
	}
	public PR_member findByFirstName(String firstName) {
		List<PR_member> members = mr.findAll();
		for(PR_member m : members) {
			if (m.getFirstName().equalsIgnoreCase(firstName)) {
				return m;
			}
		}
		return null;
	}
	public PR_member azurirajKorisnika(PR_member m) {
		Optional<PR_member> memberTmp = mr.findById(m.getId());
		if(memberTmp.isPresent()) {
			PR_member m1 = findByFirstName(m.getLastName());
			if(m1 == null) {
				PR_member a = memberTmp.get();
				a.setFirstName(m.getFirstName());
				a.setLastName(m.getLastName());
				a.setContactInfo(m.getContactInfo());
				return mr.save(a);
			}
			else {
				return m1;
			}
		}
		else {
			PR_member m1 = findByFirstName(m.getFirstName());
			if(m1 == null) {
				PR_member a = new PR_member();
				a.setFirstName(m.getFirstName());
				a.setLastName(m.getLastName());
				a.setContactInfo(m.getContactInfo());
				return mr.save(a);
			}
			else {
				return m1;}
			}
			
	}
	
	public PR_member findId(int id) {
		return mr.findById(id).get();
	}
	
	public void deleteMember(int id) {
		PR_member m = mr.findById(id).get();
		List<PR_loan> loans = lr.findByPrMember(m);
		lr.deleteAll(loans);
		mr.delete(m);
	}
	
}
