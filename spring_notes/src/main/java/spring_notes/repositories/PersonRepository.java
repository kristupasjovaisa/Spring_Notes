package spring_notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_notes.entities.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByPersonUUID(UUID id);
}
