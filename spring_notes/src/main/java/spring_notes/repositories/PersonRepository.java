package spring_notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_notes.entities.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
