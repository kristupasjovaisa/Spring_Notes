package spring_notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_notes.entities.Note;

public interface NoteRepository extends JpaRepository<Note,Long> {
}