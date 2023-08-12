package com.school.copyManagement.controller;

import com.school.copyManagement.dto.request.CourseRequest;
import com.school.copyManagement.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //CREATE COURSE
    @PostMapping("/create-course/teatcher/{idTeatcher}")
    public ResponseEntity<Object> createCourse (@RequestBody CourseRequest courseRequest, @PathVariable("idTeatcher") Long idTeatcher) {
        return courseService.createCourse(courseRequest, idTeatcher);
    }

    // FIND ALL COURSES
    @GetMapping("/all")
    public ResponseEntity<Object> findAllUser() {
        return courseService.findAll();
    }

    // FIND USER BY ID
    @GetMapping("/id")
    public ResponseEntity<Object> findCourseById(@RequestParam Long id) {
        return courseService.findCourseById(id);
    }
}
