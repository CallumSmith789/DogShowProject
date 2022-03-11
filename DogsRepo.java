package com.example.dogs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dogs.domain.Dogs;

@Repository
public interface DogsRepo extends JpaRepository<Dogs, Integer> {
	
	List<Dogs> findByBreed(String breed);
	
	List<Dogs> findByName(String name);
	
	List<Dogs> findByAge(Integer age);
	
	List<Dogs> findBySkill(String skill);

}
