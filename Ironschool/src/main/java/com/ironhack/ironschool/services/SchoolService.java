package com.ironhack.ironschool.services;

import com.ironhack.ironschool.models.Course;
import com.ironhack.ironschool.models.Student;
import com.ironhack.ironschool.models.Teacher;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class SchoolService {

    private final Map<String, Teacher> teacherMap = new HashMap<>();
    private final Map<String, Course> courseMap = new HashMap<>();
    private final Map<String, Student> studentMap = new HashMap<>();

    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getTeacherId()  , teacher);
    }
    public void addCourse(Course course) {
        courseMap.put(course.getCourseId(), course);

    }
    public void addStudent(Student student) {
        studentMap.put(student.getStudentId(), student);

    }


    public void enrollStudent(String studentId, String courseId) {
        Student student = studentMap.get(studentId);
        Course course =  courseMap.get(courseId);

        if (student == null) throw new IllegalArgumentException("Student ID not found: " + studentId);
        if  (course == null ) throw new IllegalArgumentException("Course ID not found: " + courseId);

        student.setCourse(course);


        double  currentMoney = course.getMoney_earned();
        course.setMoney_earned(currentMoney + course.getPrice());


    }

    public void assignTeacher(String teacherId, String courseId) {
        Teacher teacher = teacherMap.get(teacherId);
        Course course = courseMap.get(courseId);

        if (teacher == null) throw new IllegalArgumentException("Teacher ID not found: " + teacherId);
        if (course == null) throw new IllegalArgumentException("Course ID not found: " + courseId);

        course.setTeacher(teacher);
    }

    public double calculateProfit() {
        double totalRevenue = 0;
        double totalSalaries = 0;



        for  (Course course : courseMap.values()) {

            totalRevenue += course.getMoney_earned( );
        }

        for (Teacher teacher : teacherMap.values()) {
            totalSalaries += teacher.getSalary();
        }

        return totalRevenue - totalSalaries;
    }

    public Collection<Teacher> getAllTeachers() { return teacherMap.values(); }
    public Collection<Course> getAllCourses() { return courseMap.values(); }
    public Collection<Student> getAllStudents() { return studentMap.values(); }

    public Teacher getTeacherById(String id) { return teacherMap.get(id); }
    public Course getCourseById(String id) { return courseMap.get(id); }
    public Student getStudentById(String id) { return studentMap.get(id); }
}