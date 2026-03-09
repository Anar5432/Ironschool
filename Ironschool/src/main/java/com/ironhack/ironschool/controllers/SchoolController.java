package com.ironhack.ironschool.controllers;

import com.ironhack.ironschool.models.Course;
import com.ironhack.ironschool.models.Student;
import com.ironhack.ironschool.models.Teacher;
import com.ironhack.ironschool.services.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }


    @GetMapping("/students")
    public Collection<Student> viewStudents() {
        return schoolService.getAllStudents();
    }

    @GetMapping("/courses")
    public Collection<Course> viewCourses() {
        return schoolService.getAllCourses();
    }

    @GetMapping("/teachers")
    public Collection<Teacher> viewTeachers() {
        return schoolService.getAllTeachers();
    }

    @GetMapping("/profit")
    public String viewProfit() {
        return "Total Profit: $" + schoolService.calculateProfit();
    }
}
