package org.example.services;

import org.example.entities.Person;
import org.example.repositories.PersonRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> getPersons() {
        return personRepo.findAll();
    }

    public void showPersonsLastName(List<Person> persons) {
        persons.forEach(p -> System.out.println("Last name: " + p.getFullName().split(" ")[0]));
    }
}
