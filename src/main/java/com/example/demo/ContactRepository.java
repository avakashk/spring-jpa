package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

	List<Contact> findByEmail(String email);
	

	
	List<Contact> searchByKeyword(String keyword);

	
	
}
