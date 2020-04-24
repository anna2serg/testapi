package ru.spb.pes.testapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.spb.pes.testapi.domain.Person;
import ru.spb.pes.testapi.dto.PersonDto;
import ru.spb.pes.testapi.exceptions.NotFoundException;
import ru.spb.pes.testapi.model.PersonModel;

@RestController
@RequestMapping("/api")
public class PersonController {
	
	private final PersonModel personModel;
	
    @Autowired
    public PersonController(PersonModel personModel) {
        this.personModel = personModel;
    }	
	
	@GetMapping(value = {"/"})
	public String index() {
		return "api 1.0";
	}
    
	@GetMapping(value = {"/get"})
	public List<PersonDto> list() {
		List<PersonDto> result = new ArrayList<>();
		for (Person person : personModel.all()) {
			result.add(PersonDto.toDto(person));
		}
		return result;
	}  
	
    @GetMapping("/get/{id}")
    public PersonDto get(@PathVariable Integer id) {
    	 Person person = personModel.get(id);
    	 if (person == null) throw new NotFoundException();
         return PersonDto.toDto(person);
    }	
    
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto create(@Valid @RequestBody PersonDto dto) {
        Person newPerson = PersonDto.toDomainObject(dto);
        newPerson = personModel.save(newPerson);
        return PersonDto.toDto(newPerson);
    }	
    
    @PutMapping("/put/{id}")
    public PersonDto update(@PathVariable Integer id, @Valid @RequestBody PersonDto dto) {
    	Person updPerson = PersonDto.toDomainObject(dto);
        updPerson = personModel.update(id, updPerson);
        return PersonDto.toDto(updPerson);
    }	
    	
}
