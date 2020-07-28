package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRESTController {

	@Autowired
	ContactRepository repository;

	@GetMapping("/contacts")
	public Iterable<Contact> getContacts() {
		return repository.findAll();
	}
	
	@PostMapping("/create")
	public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
		Contact contactFromDB =repository.save(contact);
		return new ResponseEntity<Contact>(contactFromDB, HttpStatus.OK);
	}
	@DeleteMapping("/delete/{email}")
	public ResponseEntity deleteContact(@PathVariable("email") String email) {
		List<Contact> contactList=repository.findByEmail(email);
		if(contactList!=null)
		{
			Contact contact=contactList.get(0);
			repository.delete(contact);
		}else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	@GetMapping("/getall/{place}")
	public ResponseEntity<List<Contact>> getByPlace(@PathVariable("contact") String place)
	{
		List<Contact> contacts=repository.getByPlace(place);
		
		return new ResponseEntity<List<Contact>>(contacts,HttpStatus.OK);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<Contact>> search(@PathVariable("keyword") String keyword)
	{
		
	}

}
