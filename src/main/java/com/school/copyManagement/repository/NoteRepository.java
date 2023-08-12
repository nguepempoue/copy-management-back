package com.school.copyManagement.repository;

import com.school.copyManagement.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select n from Note n where n.course.id=:idCourse")
    List<Note> fintNoteByIdCourse(Long idCourse);
}
