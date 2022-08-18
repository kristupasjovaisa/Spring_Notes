package spring_notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_notes.entities.Note;

import java.util.Optional;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note,Long> {
    Optional<Note> findByNoteUUID(UUID id);
}