package spring_notes.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_notes.dto.AddPersonRequest;
import spring_notes.dto.PersonResponse;
import spring_notes.dto.UpdatePersonRequest;
import spring_notes.entities.Person;
import spring_notes.mapper.PersonMapper;
import spring_notes.repositories.PersonRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper mapper;

    public PersonResponse add(AddPersonRequest dto) {
        return mapper.mapFrom(personRepository.save(mapper.mapFrom(dto)));
    }

    @Transactional
    public PersonResponse update(UpdatePersonRequest dto) {
        return mapper.mapFrom(personRepository.save(mapper.mapFrom(dto)));
    }

    @Transactional
    public boolean delete(UUID id) {
        Optional<Person> person = personRepository.findByPersonUUID(id);
        if (person.isPresent()) {
            person.ifPresent(value -> personRepository.deleteById(value.getId()));
            return true;
        }
        return false;
    }

    public List<PersonResponse> getAllPersons() {
        var list = personRepository.findAll();
        if (list != null) {
            return list.stream()
                    .map(mapper::mapFrom)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
