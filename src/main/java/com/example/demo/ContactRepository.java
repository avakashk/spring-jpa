package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

	List<Contact> findByEmail(String email);
	
	@Query("Select * from contact c ")
	List<Contact> getByPlace(String name);
	
}
