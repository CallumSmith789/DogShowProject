package com.example.dogs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dogs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = false)
	private String breed;
	
	@Column(nullable = false)
	private String name;
	
	private Integer age;
	
	private String skill;
	
	public Dogs() {
		super();
	}

	public Dogs(Integer id, String breed, String name, Integer age, String skill) {
		super();
		this.id = id;
		this.breed = breed;
		this.name = name;
		this.age = age;
		this.skill = skill;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	@Override
	public String toString() {
		return "Dogs [breed=" + breed + ", name=" + name + ", age=" + age + ", skill=" + skill + "]";
	}

}
