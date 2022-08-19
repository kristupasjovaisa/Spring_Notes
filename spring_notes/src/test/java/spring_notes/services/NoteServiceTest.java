package spring_notes.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spring_notes.dto.*;
import spring_notes.entities.Note;
import spring_notes.entities.Person;
import spring_notes.mapper.NoteMapper;
import spring_notes.repositories.NoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    private NoteMapper mapper;

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    @DisplayName("Shoud add Notes")
    void add() {
        Note note = Note
                .builder()
                .noteUUID(UUID.fromString("c44c3a16-fa2e-4846-af65-563400d2c72e"))
                .build();
        Note addedNote = Note
                .builder()
                .noteUUID(UUID.fromString("a680feca-728d-47c0-acdd-d359ed22293b"))
                .build();

        AddNoteRequest input = AddNoteRequest.builder().build();
        NoteResponse noteDto = NoteResponse
                .builder()
                .noteUUID(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"))
                .build();
        // Maps from addNoteDto to Note entity
        Mockito.when(mapper.mapFrom(input)).thenReturn(note);
        //Save note entity
        Mockito.when(noteRepository.save(note)).thenReturn(addedNote);
        // Maps from added note entity to person dto
        Mockito.when(mapper.mapFrom(addedNote)).thenReturn(noteDto);

        NoteResponse actual = noteService.add(input);

        Assertions.assertThat(actual.getNoteUUID()).isEqualTo(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"));
    }

    @Test
    void update() {
        Note note = Note
                .builder()
                .id(1l)
                .build();

        Note updatedNote = Note
                .builder()
                .noteUUID(UUID.fromString("99f29b98-548e-44c3-8e5f-44476c142603"))
                .build();

        UpdateNoteRequest updateNoteDto = UpdateNoteRequest.builder()
                .noteUUID(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"))
                .build();

        NoteResponse updatedNoteDto = NoteResponse
                .builder()
                .noteUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .build();
        //Find note entity by uuid
        Mockito.when(noteRepository.findByNoteUUID(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"))).thenReturn(Optional.of(note));
        //Map from update note dto and note id to updated note entity
        Mockito.when(mapper.mapFrom(updateNoteDto, 1l)).thenReturn(updatedNote);
        //Save and return updated note entity
        Mockito.when(noteRepository.save(updatedNote)).thenReturn(updatedNote);
        //Map from updated note entity to updated note dto
        Mockito.when(mapper.mapFrom(updatedNote)).thenReturn(updatedNoteDto);
        //Pass update  note dto and return updated note dto
        Optional<NoteResponse> actual = noteService.update(updateNoteDto);
        Assertions.assertThat(actual.get().getNoteUUID()).isEqualTo(UUID.fromString("00000000-0000-0000-0000-000000000000"));
    }

    @Test
    void delete() {
        Note note = Note.builder()
                .id(1l)
                .build();
        Mockito.when(noteRepository.findByNoteUUID(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"))).thenReturn(Optional.of(note));
        boolean actual = noteService.delete(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"));
        Mockito.verify(noteRepository, Mockito.times(1)).deleteById(1l);
        Assertions.assertThat(actual).isEqualTo(true);
    }

    @Test
    void getAllNotes() {
        List<Note> list = new ArrayList<>();
        Note note1 = Note.builder().noteUUID(UUID.fromString("fe2891c7-348e-4de4-8061-62f7be4dadea")).
                build();
        list.add(note1);

        NoteResponse noteDto = NoteResponse.builder().noteUUID(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"))
                .build();

        Mockito.when(noteRepository.findAll()).thenReturn(list);
        Mockito.when(mapper.mapFrom(note1)).thenReturn(noteDto);

        List<NoteResponse> actual = noteService.getAllNotes();
        Assertions.assertThat(actual.get(0).getNoteUUID()).isEqualTo(UUID.fromString("e4dbc123-a7c2-4bee-a519-e1b9ba991358"));
        Assertions.assertThat(actual.size()).isEqualTo(1);
    }
}