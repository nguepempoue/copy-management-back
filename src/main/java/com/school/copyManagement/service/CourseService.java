package com.school.copyManagement.service;

import com.school.copyManagement.dto.request.CourseRequest;
import com.school.copyManagement.dto.response.ResponseHandler;
import com.school.copyManagement.model.Course;
import com.school.copyManagement.model.Note;
import com.school.copyManagement.model.User;
import com.school.copyManagement.repository.CourseRepository;
import com.school.copyManagement.repository.UserRepository;
import com.school.copyManagement.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }


    // CREATE COURSE
    public ResponseEntity<Object> createCourse(CourseRequest courseRequest, Long idTeatcher) {

        Optional<User> user = userRepository.findById(idTeatcher);

        // NEW ROLE
        Course c = new Course();

        try {

            if(!user.isPresent()){
                throw new Exception("User not found !");
            }
            AtomicBoolean isTeatcher = new AtomicBoolean(false);
            user.get().getRoles().forEach(role -> {
                if(role.getName().equals("TEATCHER")){
                    isTeatcher.set(true);
                };
            });
            // SETTING VALUES
            if(!isTeatcher.get()){
                throw new Exception("The user is not an teatcher !");
            }
            c.setTeatcher(user.get());
            c.setName(courseRequest.getName());
            c.setDescription(courseRequest.getDescription());
            c.setEvaluationNote(courseRequest.getEvaluationNote());
            //SAVE AND RETURN
            return ResponseHandler.generateResponse("This course has been saved !", HttpStatus.CREATED,  courseRepository.save(c));
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public ResponseEntity<Object> findAll() {
        try {
            // GETTING ALL COURSE
            List<Course> courses = this.courseRepository.findAll();
            if(courses.isEmpty())
            {
                return null;
            }
            return ResponseHandler.generateOkResponse("Course list", courses);
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return ResponseHandler.generateError(e);
        }
    }

    // FIND COURSE BY ID
    public ResponseEntity<Object> findCourseById(Long id) {

        // GET ROLE
        Optional<Course> course = courseRepository.findById(id);
        try {
            if(!course.isPresent()) {
                System.out.println("Course not found !");
                return null;
            }
            return ResponseHandler.generateOkResponse("Course", course.get());

        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    // DELETE COURSE
    public ResponseEntity<Object> deleteCourse(Long idCourse) {
        // GET COURSE
        Optional<Course> course = courseRepository.findById(idCourse);

        try {
            if (!course.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Course not found !");
            }


            courseRepository.delete(course.get());
            return ResponseHandler.generateOkResponse("Course properly deleted !",
                    null);
        } catch (Exception e) {
            return Utils.catchException(e);
        }
    }

    public ResponseEntity<Object> fintNoteByIdCourse(Long idUser) {
        try {
            // GETTING ALL COURSE
            List<Course> courses = this.courseRepository.fintCourseByIdidTeatcher(idUser);
            if(courses.isEmpty())
            {
                return null;
            }
            return ResponseHandler.generateOkResponse("Courses list", courses);
        }
        catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return ResponseHandler.generateError(e);
        }
    }
}
