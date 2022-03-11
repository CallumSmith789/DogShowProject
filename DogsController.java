package com.example.dogs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dogs.domain.Dogs;
import com.example.dogs.service.DogsService;

@RestController
public class DogsController {
	
	private DogsService service;
	
	@Autowired
	public DogsController(DogsService service) {
		super();
		this.service=service;
	}

	@PostMapping("/create")

	public ResponseEntity<Dogs> createDogs(@RequestBody Dogs d) {
		Dogs created = this.service.create(d);
		ResponseEntity<Dogs> response = new ResponseEntity<Dogs>(created, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/getAll") 
	public ResponseEntity<List<Dogs>> getAllDogs() {
		return ResponseEntity.ok(this.service.getAll()); 
	}
	
	@GetMapping("/get/{id}") 
	public Dogs getDog(@PathVariable Integer id) {
		return this.service.getOne(id);
	}
	@PutMapping("/replace/{id}")
	public ResponseEntity<Dogs> replaceDog(@PathVariable Integer id, @RequestBody Dogs newDog) {
		Dogs body = this.service.replace(id, newDog);
		ResponseEntity<Dogs> response = new ResponseEntity<Dogs>(body, HttpStatus.ACCEPTED);
		return response;
	}
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeCar(@PathVariable Integer id) {
		this.service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getByBreed/{breed}")
	public ResponseEntity<List<Dogs>> getDogsByBreed(@PathVariable String breed){
		List<Dogs> found = this.service.getAllDogsByBreed(breed);
		return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Dogs>> getDogsByName(@PathVariable String name){
		List<Dogs> found = this.service.getAllDogsByName(name);
		return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getByAge/{age}")
	public ResponseEntity<List<Dogs>> getDogsByAge(@PathVariable Integer age){
		List<Dogs> found = this.service.getAllDogsByAge(age);
		return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getBySkill/{skill}")
	public ResponseEntity<List<Dogs>> getDogsBySkill(@PathVariable String skill){
		List<Dogs> found = this.service.getAllDogsBySkill(skill);
		return ResponseEntity.ok(found);
	}

}
