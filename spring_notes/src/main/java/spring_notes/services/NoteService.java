package spring_notes.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_notes.dto.*;
import spring_notes.entities.Note;
import spring_notes.mapper.NoteMapper;
import spring_notes.repositories.NoteRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper mapper;

    public NoteResponse add(AddNoteRequest dto) {
        return mapper.mapFrom(noteRepository.save(mapper.mapFrom(dto)));
    }

    @Transactional
    public Optional<NoteResponse> update(UpdateNoteRequest dto) {
        Optional<Note> noteOptional = noteRepository.findByNoteUUID(dto.getNoteUUID());
        if (noteOptional.isPresent()) {
            var note = mapper.mapFrom(dto, noteOptional.get().getId());
            return Optional.of(mapper.mapFrom(noteRepository.save(note)));
        }
        return Optional.empty();
    }

    @Transactional
    public boolean delete(UUID id) {
        Optional<Note> note = noteRepository.findByNoteUUID(id);
        if (note.isPresent()) {
            noteRepository.deleteById(note.get().getId());
            return true;
        }
        return false;
    }

    public List<NoteResponse> getAllNotes() {
        var list = noteRepository.findAll();
        if (list != null) {
            return list.stream()
                    .map(mapper::mapFrom)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
