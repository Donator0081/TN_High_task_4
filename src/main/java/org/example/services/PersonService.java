package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.entities.Person;
import org.example.repositories.PersonRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepo personRepo;

    public List<Person> getPersons() {
        return personRepo.findAll();
    }

    public void showPersonsLastName(List<Person> persons) {
        persons.forEach(p -> System.out.println("Last name: " + p.getFullName().split(" ")[0]));
    }
}
