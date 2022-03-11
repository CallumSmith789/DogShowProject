package com.example.dogs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dogs.domain.Dogs;
import com.example.dogs.repo.DogsRepo;

@Service
public class DogsService implements ServiceInterface<Dogs>{

	private DogsRepo repo;
	
	@Autowired
	public DogsService(DogsRepo repo) {
		super();
		this.repo=repo;
	}
	
	public Dogs create(Dogs d) {
		Dogs created = this.repo.save(d);
		return created;
	}
	
	public List<Dogs> getAll(){
		return this.repo.findAll();
	}
	
	public Dogs getOne(Integer id) {
		Optional<Dogs> found = this.repo.findById(id);
		return found.get();
	}
	
	public List<Dogs> getAllDogsByBreed(String breed){
		List<Dogs> found = this.repo.findByBreed(breed);
		return found;
	}
	
	public List<Dogs> getAllDogsByName(String name){
		List<Dogs> found = this.repo.findByName(name);
		return found;
	}
	
	public List<Dogs> getAllDogsByAge(Integer age){
		List<Dogs> found = this.repo.findByAge(age);
		return found;
	}
	
	public List<Dogs> getAllDogsBySkill(String skill){
		List<Dogs> found = this.repo.findBySkill(skill);
		return found;
	}
	
	public Dogs replace(Integer id, Dogs newDogs) {
		Dogs existing = this.repo.findById(id).get();
		existing.setBreed(newDogs.getBreed());
		existing.setName(newDogs.getName());
		existing.setAge(newDogs.getAge());
		existing.setSkill(newDogs.getSkill());
		Dogs updated = this.repo.save(existing);
		return updated;
	}
	
	public void remove(@PathVariable Integer id) {
		this.repo.deleteById(id);
	}
}
