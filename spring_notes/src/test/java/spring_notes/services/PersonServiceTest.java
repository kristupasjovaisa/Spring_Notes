package spring_notes.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spring_notes.dto.AddPersonRequest;
import spring_notes.dto.PersonResponse;
import spring_notes.dto.UpdatePersonRequest;
import spring_notes.entities.Person;
import spring_notes.mapper.PersonMapper;
import spring_notes.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonMapper mapper;
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;

    @Test
    @DisplayName("Shoud add Persons")
    void add() {
        Person person = Person
                .builder()
                .personUUID(UUID.fromString("c44c3a16-fa2e-4846-af65-563400d2c72e"))
                .build();
        Person addedPerson = Person
                .builder()
                .personUUID(UUID.fromString("a680feca-728d-47c0-acdd-d359ed22293b"))
                .build();

        AddPersonRequest input = AddPersonRequest.builder().build();
        PersonResponse personDto = PersonResponse
                .builder()
                .personUUID(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"))
                .build();
        // Maps from addPersonDto to Person entity
        Mockito.when(mapper.mapFrom(input)).thenReturn(person);
        //Save person entity
        Mockito.when(personRepository.save(person)).thenReturn(addedPerson);
        // Maps from added person entity to person dto
        Mockito.when(mapper.mapFrom(addedPerson)).thenReturn(personDto);

        PersonResponse actual = personService.add(input);

        Assertions.assertThat(actual.getPersonUUID()).isEqualTo(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"));
    }

    @Test
    @DisplayName("Shoud update Person by id")
    void update() {
        Person person = Person
                .builder()
                .id(1l)
                .build();

        Person updatedPerson = Person
                .builder()
                .personUUID(UUID.fromString("99f29b98-548e-44c3-8e5f-44476c142603"))
                .build();

        UpdatePersonRequest updatePersonDto = UpdatePersonRequest.builder()
                .personUUID(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"))
                .build();

        PersonResponse updatedPersonDto = PersonResponse
                .builder()
                .personUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .build();
        //Find person entity by uuid
        Mockito.when(personRepository.findByPersonUUID(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"))).thenReturn(Optional.of(person));
        //Map from update person dto and person id to updated person entity
        Mockito.when(mapper.mapFrom(updatePersonDto, 1l)).thenReturn(updatedPerson);
        //Save and return updated person entity
        Mockito.when(personRepository.save(updatedPerson)).thenReturn(updatedPerson);
        //Map from updated person entity to updated person dto
        Mockito.when(mapper.mapFrom(updatedPerson)).thenReturn(updatedPersonDto);
        //Pass update  person dto and return updated person dto
        Optional<PersonResponse> actual = personService.update(updatePersonDto);
        Assertions.assertThat(actual.get().getPersonUUID()).isEqualTo(UUID.fromString("00000000-0000-0000-0000-000000000000"));
    }

    @Test
    @DisplayName("Shoud delete Person by Id")
    void delete() {
        Person person = Person.builder()
                .id(1l)
                .build();
        Mockito.when(personRepository.findByPersonUUID(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"))).thenReturn(Optional.of(person));
        boolean actual = personService.delete(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"));
        Mockito.verify(personRepository, Mockito.times(1)).deleteById(1l);
        Assertions.assertThat(actual).isEqualTo(true);
    }

    @Test
    void getAllPersons() {
        List<Person> list = new ArrayList<>();
        Person person1 = Person.builder().personUUID(UUID.fromString("fe2891c7-348e-4de4-8061-62f7be4dadea")).
                build();
        list.add(person1);

        PersonResponse personDto = PersonResponse.builder().personUUID(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"))
                .build();

        Mockito.when(personRepository.findAll()).thenReturn(list);
        Mockito.when(mapper.mapFrom(person1)).thenReturn(personDto);

        List<PersonResponse> actual = personService.getAllPersons();
        Assertions.assertThat(actual.get(0).getPersonUUID()).isEqualTo(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"));
        Assertions.assertThat(actual.size()).isEqualTo(1);
    }
}