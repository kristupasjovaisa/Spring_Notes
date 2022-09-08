package spring_notes.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_notes.dto.AddPersonRequest;
import spring_notes.dto.PersonResponse;
import spring_notes.dto.UpdatePersonRequest;
import spring_notes.services.PersonService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(PersonApiController.PERSON_ROOT_PATH)
@RequiredArgsConstructor
public class PersonApiController {

    private final PersonService personService;
    public static final String PERSON_ROOT_PATH = "/persons";
    public static final String PERSON_UUID_PATH = "/{uuid}";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonResponse>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping(path = PERSON_UUID_PATH)
    public ResponseEntity<Optional<PersonResponse>> getByPersonUUID(@PathVariable("uuid") UUID id) {
        var optionalPerson = personService.getPerson(id);
        if (optionalPerson.isPresent()) {
            return ResponseEntity.ok(optionalPerson);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> addPerson(AddPersonRequest person) {
        return new ResponseEntity<>(personService.add(person), HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> updatePerson(UpdatePersonRequest person) {
        var optionalPerson = personService.update(person);
        if (optionalPerson.isPresent()) {
            return new ResponseEntity<>(optionalPerson.get(), HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = PERSON_UUID_PATH)
    public ResponseEntity deletePerson(@PathVariable("uuid") UUID id) {
        if (personService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
