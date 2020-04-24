package ru.spb.pes.testapi.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import ru.spb.pes.testapi.domain.Person;

public class PersonDto {
	
	@NotNull(message = "Please provide a id")
	@Min(1)
    private Integer id;
	
	@NotNull(message = "Please provide a name")
    private String name;
	
	@NotNull(message = "Please provide a surname")
    private String surname;

    /*public PersonDto() {
    }*/    
    
    public PersonDto(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
    public static Person toDomainObject(PersonDto dto) {
        return new Person(dto.getId(), dto.getName(), dto.getSurname());
    }

    public static PersonDto toDto(Person person) {
        return new PersonDto(person.getId(), person.getName(), person.getSurname());
    }
	
}
