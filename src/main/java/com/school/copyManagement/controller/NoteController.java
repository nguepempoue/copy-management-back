package com.school.copyManagement.controller;

import com.school.copyManagement.dto.request.CourseRequest;
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

    // FIND ALL COURSES
    @GetMapping("/all")
    public ResponseEntity<Object> findAllUser() {
        return noteService.findAll();
    }
}
