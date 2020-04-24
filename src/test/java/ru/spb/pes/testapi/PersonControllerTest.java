package ru.spb.pes.testapi;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.spb.pes.testapi.controller.PersonController;
import ru.spb.pes.testapi.domain.Person;
import ru.spb.pes.testapi.model.PersonModel;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebMvcTest(PersonController.class)
public class PersonControllerTest {
	
	private static final ObjectMapper om = new ObjectMapper();
	
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private PersonModel mockModel;
    
    @Test
    public void getPerson_Ok() throws Exception {
    	
    	Person person = new Person(777, "Michael", "Lermontov");
    	
    	given(mockModel.get(any(Integer.class))).willReturn(person);
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get/777"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(777)))
               .andExpect(jsonPath("$.name", is("Michael")))
               .andExpect(jsonPath("$.surname", is("Lermontov"))); 	
        
    }
    
    @Test
    public void getPerson_NotFound() throws Exception {
    	
    	given(mockModel.get(any(Integer.class))).willReturn(null);
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get/777"))
               .andExpect(status().isNotFound()); 	
        
    }    
    
    @Test
    public void addPerson_Ok() throws Exception {
    	
        Person person = new Person(888, "Alex", "Pushkin");

        String personString = om.writeValueAsString(person);
        given(mockModel.save(any(Person.class))).willReturn(person);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
        	   .content(personString)
        	   .contentType(MediaType.APPLICATION_JSON_UTF8))
        	   .andExpect(status().isCreated())
               .andExpect(jsonPath("$.id", is(888)))
               .andExpect(jsonPath("$.name", is("Alex")))
               .andExpect(jsonPath("$.surname", is("Pushkin"))); 	  	
    }
    
    @Test
    public void addPerson_BadRequest() throws Exception {
    	
        Person person = new Person("Alex", "Pushkin");

        String personString = om.writeValueAsString(person);
        given(mockModel.save(any(Person.class))).willReturn(person);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
        	   .content(personString)
        	   .contentType(MediaType.APPLICATION_JSON_UTF8))
        	   .andExpect(status().isBadRequest()); 	  	
    }  
    
    @Test
    public void updatePerson_Ok() throws Exception {
    	
        Person person = new Person(888, "Alex", "Pushkinidze");

        String personString = om.writeValueAsString(person);
        given(mockModel.update(any(Integer.class), any(Person.class))).willReturn(person);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/put/888")
        	   .content(personString)
        	   .contentType(MediaType.APPLICATION_JSON_UTF8))
        	   .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(888)))
               .andExpect(jsonPath("$.name", is("Alex")))
               .andExpect(jsonPath("$.surname", is("Pushkinidze"))); 	  	
    }    
    
    @Test
    public void updatePerson_BadRequest() throws Exception {
    	
        Person person = new Person("Alex", "Pushkinidze");

        String personString = om.writeValueAsString(person);
        given(mockModel.update(any(Integer.class), any(Person.class))).willReturn(person);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/put/888")
        	   .content(personString)
        	   .contentType(MediaType.APPLICATION_JSON_UTF8))
        	   .andExpect(status().isBadRequest()); 	  	
    }         

}
