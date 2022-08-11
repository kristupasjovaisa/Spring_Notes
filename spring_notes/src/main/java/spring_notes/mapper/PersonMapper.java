package spring_notes.mapper;

import org.springframework.stereotype.Component;
import spring_notes.dto.AddPersonRequest;
import spring_notes.dto.PersonResponse;
import spring_notes.dto.UpdatePersonRequest;
import spring_notes.entities.Person;

import java.util.UUID;

@Component
public class PersonMapper {

    public Person mapFrom(AddPersonRequest dto) {
        return Person.builder().
                personUUID(UUID.randomUUID()).
                name(dto.getName()).
                lastName(dto.getLastName()).
                email(dto.getEmail()).
                build();
    }

    public Person mapFrom(UpdatePersonRequest dto) {
        return Person.builder().
                personUUID(dto.getPersonUUID()).
                name(dto.getName()).
                lastName(dto.getLastName()).
                email(dto.getEmail()).
                build();
    }

    public PersonResponse mapFrom(Person person) {
        return PersonResponse.builder().
                personUUID(person.getPersonUUID()).
                name(person.getName()).
                lastName(person.getLastName()).
                email(person.getEmail()).
                build();
    }
}