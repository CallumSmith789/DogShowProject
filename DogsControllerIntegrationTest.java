package com.example.dogs.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.dogs.domain.Dogs;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts= {"classpath:dogs-schema.sql", "classpath:dogs-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class DogsControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper; 
	
	@Test
	void testCreate() throws Exception {
		Dogs testDogs = new Dogs(null, "Labrador", "Max", 4, "Walking on hind legs");
		String testDogsAsJSON=this.mapper.writeValueAsString(testDogs);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testDogsAsJSON);
		
		Dogs testCreatedDogs = new Dogs(3, "Labrador", "Max", 4, "Walking on hind legs");
		String testCreatedDogsAsJSON = this.mapper.writeValueAsString(testCreatedDogs);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedDogsAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void getAllTest() throws Exception{
		RequestBuilder req = get("/getAll");
		List<Dogs> testDogs = List.of(new Dogs(1, "Labrador", "Max", 4, "Walking on hind legs"), new Dogs(2, "Golden Retriever", "Sam", 5, "Jumping through hoop"));
		String json = this.mapper.writeValueAsString(testDogs);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getIdTest() throws Exception{
		RequestBuilder req = get("/get/1");
		Dogs testDogs = new Dogs(1, "Labrador", "Max", 4, "Walking on hind legs");
		String json = this.mapper.writeValueAsString(testDogs);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testReplace() throws Exception {
		Dogs testDogs = new Dogs(null, "Labrador", "Max", 4, "Walking on hind legs");
		String testDogsAsJSON=this.mapper.writeValueAsString(testDogs);
		RequestBuilder req = put("/replace/2").contentType(MediaType.APPLICATION_JSON).content(testDogsAsJSON);
		
		Dogs testUpdatedDogs = new Dogs(2, "Labrador", "Max", 4, "Walking on hind legs");
		String testUpdatedDogsAsJSON = this.mapper.writeValueAsString(testUpdatedDogs);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testUpdatedDogsAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testRemove() throws Exception {
		RequestBuilder req = delete("/remove/1");
		ResultMatcher checkStatus = status().isNoContent();
		
		this.mvc.perform(req).andExpect(checkStatus);
	}
	
	@Test
	void getBreedTest() throws Exception{
		RequestBuilder req = get("/getByBreed/Labrador");
		List<Dogs> testDogs = List.of(new Dogs(1, "Labrador", "Max", 4, "Walking on hind legs"));
		String getBreedTestAsJSON = this.mapper.writeValueAsString(testDogs);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(getBreedTestAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getNameTest() throws Exception{
		RequestBuilder req = get("/getByName/Max");
		List<Dogs> testDogs = List.of(new Dogs(1, "Labrador", "Max", 4, "Walking on hind legs"));
		String getNameTestAsJSON = this.mapper.writeValueAsString(testDogs);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(getNameTestAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getAgeTest() throws Exception{
		RequestBuilder req = get("/getByAge/4");
		List<Dogs> testDogs = List.of(new Dogs(1, "Labrador", "Max", 4, "Walking on hind legs"));
		String getAgeTestAsJSON = this.mapper.writeValueAsString(testDogs);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(getAgeTestAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getSkillTest() throws Exception{
		RequestBuilder req = get("/getBySkill/Walking on hind legs");
		List<Dogs> testDogs = List.of(new Dogs(1, "Labrador", "Max", 4, "Walking on hind legs"));
		String getSkillTestAsJSON = this.mapper.writeValueAsString(testDogs);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(getSkillTestAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}


}
