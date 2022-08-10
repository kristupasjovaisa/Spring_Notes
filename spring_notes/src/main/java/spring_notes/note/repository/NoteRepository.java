package spring_notes.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_notes.note.entity.Note;

public interface NoteRepository extends JpaRepository<Note,Long> {
}
