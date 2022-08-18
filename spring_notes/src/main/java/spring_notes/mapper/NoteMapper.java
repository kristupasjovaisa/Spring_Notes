package spring_notes.mapper;

import org.springframework.stereotype.Component;
import spring_notes.dto.*;
import spring_notes.entities.Note;

@Component
public class NoteMapper {

    public Note mapFrom(AddNoteRequest dto) {
        return Note.builder().
                name(dto.getName()).
                description(dto.getDescription()).
                createdAt(dto.getCreatedAt()).
                build();
    }

    public Note mapFrom(UpdateNoteRequest dto, Long id) {
        return Note.builder().
                id(id).
                noteUUID(dto.getNoteUUID()).
                name(dto.getName()).
                description(dto.getDescription()).
                createdAt(dto.getCreatedAt()).
                build();
    }

    public NoteResponse mapFrom(Note note) {
        return NoteResponse.builder().
                noteUUID(note.getNoteUUID()).
                name(note.getName()).
                description(note.getDescription()).
                createdAt(note.getCreatedAt()).
                build();
    }
}
