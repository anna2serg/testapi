package ru.spb.pes.testapi.domain;

import java.util.Objects;

public class Person {

    private Integer id;
    private String name;
    private String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(Integer id, String name, String surname) {
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

	@Override
	public int hashCode() {
		return java.util.Objects.hash(getId(), getName(), getSurname());
	}

	@Override
	public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }  	
        if (!(obj instanceof Person)) {
            return false;
        }       
        Person other = (Person)obj;
        
        return (Objects.equals(other.id, this.id)) && 
        	   (Objects.equals(other.name, this.name)) && 
			   (Objects.equals(other.surname, this.surname)); 
	}
	
	
    
}
