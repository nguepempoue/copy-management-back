package com.school.copyManagement.service;

import com.school.copyManagement.dto.request.NoteRequest;
import com.school.copyManagement.dto.response.ResponseHandler;
import com.school.copyManagement.model.Course;
import com.school.copyManagement.model.Note;
import com.school.copyManagement.model.User;
import com.school.copyManagement.repository.CourseRepository;
import com.school.copyManagement.repository.NoteRepository;
import com.school.copyManagement.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    // CREATE NOTE
    public ResponseEntity<Object> createNote(NoteRequest noteRequest, Long idStudent, Long idCourse) {

        Optional<User> student = userRepository.findById(idStudent);

        Optional<Course> course = courseRepository.findById(idCourse);

        // NEW NOTE
        Note n = new Note();

        try {

            if(!student.isPresent()){
                throw new Exception("Student not found !");
            }

            if(!course.isPresent()){
                throw new Exception("Course not found !");
            }

            if(noteRequest.getNote() > course.get().getEvaluationNote()){
                throw new Exception("Sorry, the grade cannot be lower than the course evaluation grade. !");
            }

            AtomicBoolean isStudent = new AtomicBoolean(false);
            student.get().getRoles().forEach(role -> {
                if(role.getName().equals("STUDENT")){
                    isStudent.set(true);
                };
            });
            // SETTING VALUES
            if(!isStudent.get()){
                throw new Exception("The user is not an student !");
            }
            n.setNote(noteRequest.getNote());
            n.setCourse(course.get());
            n.setStudent(student.get());
            //SAVE AND RETURN
            return ResponseHandler.generateResponse("This note has been saved !", HttpStatus.CREATED,  noteRepository.save(n));
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public ResponseEntity<Object> findAll() {
        try {
            // GETTING ALL NOTES
            List<Note> notes = this.noteRepository.findAll();
            if(notes.isEmpty())
            {
                return null;
            }
            return ResponseHandler.generateOkResponse("Notes list", notes);
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return ResponseHandler.generateError(e);
        }
    }
}
