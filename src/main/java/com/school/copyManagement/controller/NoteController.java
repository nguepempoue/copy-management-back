package com.school.copyManagement.controller;

import com.school.copyManagement.dto.request.NoteRequest;
import com.school.copyManagement.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    //CREATE COURSE
    @PostMapping("/create-note/student/{idStudent}/course/{idCourse}")
    public ResponseEntity<Object> createCourse (@RequestBody NoteRequest noteRequest, @PathVariable("idStudent") Long idStudent, @PathVariable("idCourse") Long idCourse) {
        return noteService.createNote(noteRequest, idStudent, idCourse);
    }

    // FIND ALL NOTE
    @GetMapping("/all")
    public ResponseEntity<Object> findNote() {
        return noteService.findAll();
    }


    // FIND ALL NOTE BY ID COURSE
    @GetMapping("/all-note-by/{idCourse}")
    public ResponseEntity<Object> fintNoteByIdCourse( @PathVariable("idCourse") Long idCourse) {
        return noteService.fintNoteByIdCourse(idCourse);
    }

    // DELETE
    @DeleteMapping("/{idNote}")
    public ResponseEntity<Object> deleteNote(@PathVariable("idNote") Long idNote) {
        return noteService.deleteNote(idNote);
    }
}
