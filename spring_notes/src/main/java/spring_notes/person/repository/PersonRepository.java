package spring_notes.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_notes.person.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
