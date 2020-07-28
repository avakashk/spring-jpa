package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlaceRepository extends CrudRepository<Place,Integer>{
	
 
	Place findByName(String name);

}
