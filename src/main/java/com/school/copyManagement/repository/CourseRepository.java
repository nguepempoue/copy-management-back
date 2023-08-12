package com.school.copyManagement.repository;

import com.school.copyManagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.teatcher.id=:idTeatcher")
    List<Course> fintCourseByIdidTeatcher(Long idTeatcher);
}
