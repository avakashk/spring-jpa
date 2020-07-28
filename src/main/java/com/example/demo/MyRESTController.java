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
	@Autowired
	PlaceRepository placeRepository;

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
		if(!contactList.isEmpty())
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
		Place placeFromDb=placeRepository.findByName(place);
		
		List<Contact> contacts=placeFromDb.getContact();
		
		return new ResponseEntity<List<Contact>>(contacts,HttpStatus.OK);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<Contact>> search(@PathVariable("keyword") String keyword)
	{
		List<Contact> contacts=repository.searchByKeyword(keyword);
		if(contacts.isEmpty())
		{
			return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Contact>>(contacts,HttpStatus.OK);
	}

}
