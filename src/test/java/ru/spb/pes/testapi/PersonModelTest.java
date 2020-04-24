package ru.spb.pes.testapi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import ru.spb.pes.testapi.domain.Person;
import ru.spb.pes.testapi.exceptions.NameUniqueViolationException;
import ru.spb.pes.testapi.model.PersonModel;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class PersonModelTest {

	@Autowired
	private PersonModel model;	
	
	@Before 
	public void Init() {
		model.save(new Person(666, "Yuri", "Gagarin"));
		model.save(new Person(777, "Michael", "Lermontov"));
	}
	
    @Test
    public void savePersonWithUniqueName_thenOk() {
    	Person newPerson = new Person(888, "Alex", "Pushkin");
    	Person dbPerson = model.save(newPerson);
    	assertEquals(newPerson, dbPerson);		
    }
	
    @Test
    public void updatePersonWithUniqueName_thenOk() {
    	Person updPerson = new Person(777, "Yuri the best", "Gagarin");
    	Person dbPerson = model.update(777, updPerson);
    	assertEquals(updPerson, dbPerson);
    }
    
    @Test(expected = NameUniqueViolationException.class)
    public void savePersonWithNonUniqueName_thenException() {
    	model.save(new Person(888, "Michael", "Pushkin"));
    }        
    
    @Test(expected = NameUniqueViolationException.class)
    public void updatePersonWithNonUniqueName_thenException() {
    	model.update(777, new Person(777, "Yuri", "Lermontov"));
    }    
	
	
}
