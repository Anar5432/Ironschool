package com.ironhack.ironschool;


import com.ironhack.ironschool.models.Course;
import com.ironhack.ironschool.models.Student;
import com.ironhack.ironschool.models.Teacher;
import com.ironhack.ironschool.services.SchoolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolServiceTest {

    private SchoolService schoolService;
    private Student testStudent;
    private Course testCourse;
    private Teacher testTeacher;


    @BeforeEach
    void setUp() {
        schoolService = new SchoolService();

        testStudent = new Student("Test Student", "123 Main St", "test@test.com");
        testCourse = new Course("Java 101", 100.0);
        testTeacher = new Teacher("Test Teacher", 40.0);

        schoolService.addStudent(testStudent);
        schoolService.addCourse(testCourse);
        schoolService.addTeacher(testTeacher);
    }

    @Test
    void testEnrollStudent_Success_AddsMoneyEarned() {
        schoolService.enrollStudent(testStudent.getStudentId(), testCourse.getCourseId());


        assertEquals(testCourse, testStudent.getCourse());



        assertEquals(100.0, testCourse.getMoney_earned());
    }

    @Test
    void testEnrollStudent_ThrowsExceptionForBadStudentId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            schoolService.enrollStudent("S-999", testCourse.getCourseId());
        } );

        assertTrue(exception.getMessage().contains("Student ID not found"));
    }


    @Test
    void testAssignTeacher_Success_AttachesTeacher() {

        schoolService.assignTeacher(testTeacher.getTeacherId(), testCourse.getCourseId());
        assertEquals(testTeacher, testCourse.getTeacher());
    }

    @Test
    void testCalculateProfit_ReturnsCorrectMath() {




        schoolService.enrollStudent(testStudent.getStudentId(), testCourse.getCourseId());

        double profit = schoolService.calculateProfit();

        assertEquals(60.0, profit);
    }
}