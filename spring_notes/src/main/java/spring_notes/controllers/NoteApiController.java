package spring_notes.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_notes.dto.AddNoteRequest;
import spring_notes.dto.NoteResponse;
import spring_notes.dto.UpdateNoteRequest;
import spring_notes.entities.Note;
import spring_notes.services.NoteService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping(NoteApiController.NOTES_ROOT_PATH)
public class NoteApiController {

    private final NoteService noteService;
    public static final String UUID_PATH = "/{uuid}";
    public static final String NOTES_ROOT_PATH = "/notes";

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @PutMapping
    public ResponseEntity<NoteResponse> updateNote(@RequestBody UpdateNoteRequest note) {
        var optionalNote = noteService.update(note);
        if (optionalNote.isPresent()) {
            return new ResponseEntity<>(optionalNote.get(), HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<NoteResponse> addNote(@RequestBody AddNoteRequest note) {
        return ResponseEntity.ok(noteService.add(note));
    }

    @DeleteMapping(path = UUID_PATH)
    public ResponseEntity deleteNote(@PathVariable("uuid") UUID id) {
        if (noteService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}