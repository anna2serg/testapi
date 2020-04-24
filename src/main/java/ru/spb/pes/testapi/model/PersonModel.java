package ru.spb.pes.testapi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ru.spb.pes.testapi.domain.Person;

@Service
public class PersonModel {
	
	private Map<Integer, Person> persons = new HashMap<Integer, Person>()
    {{
        put(1, new Person(1, "Иннокентий", "Котов"));
        put(2, new Person(2, "Нина", "Потапова"));
        put(3, new Person(3, "Александр", "Ким"));
    }};	
    
	public Person get(Integer id) {
		return persons.get(id);
	}    

	public List<Person> all() {	
		return persons.values().stream().collect(Collectors.toList());
	}

	public Person save(Person newPerson) {
		persons.put(newPerson.getId(), newPerson);
		return newPerson;
	}

	public Person update(Integer id, Person updPerson) {
        Person person = get(id);
        person.setName(updPerson.getName());
        person.setSurname(updPerson.getSurname());
        return person;
	}
    
    
}
