package com.ironhack.ironschool;
import com.ironhack.ironschool.models.Course;
import com.ironhack.ironschool.models.Student;
import com.ironhack.ironschool.models.Teacher;
import com.ironhack.ironschool.services.SchoolService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class

IronschoolApplication implements CommandLineRunner {

    private final SchoolService schoolService;


    public IronschoolApplication(SchoolService  schoolService) {
        this.schoolService = schoolService;
    }

    public static void main(String[] args) {
        SpringApplication.run(IronschoolApplication.class, args);
    }


    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to the School Management System ===");
        System.out.print("Enter   the name fr the school: ");
        String schoolName = scanner.nextLine();


        int numTeachers = getValidInt(scanner , "How many teachers should be created? ");
        for (int i = 0; i < numTeachers; i++)  {

            System.out.print("Enter name for teacher " + (i + 1) + ": ");
            String name = scanner.nextLine();
            double salary = getValidDouble(scanner,  "Enter salary for teacher " + (i + 1) + ": ");
            schoolService.addTeacher(new Teacher(name, salary));
        }



        int numCourses = getValidInt(scanner, "How many courses should be created? ");
        for (int i = 0; i < numCourses; i++) {
            System.out.print("Enter name for course " + (i + 1) + ": ");
            String name = scanner.nextLine();

            double price = getValidDouble(scanner, "Enter price for course " + (i + 1) + ": ");
            schoolService.addCourse(new Course(name, price));



        }

        int  numStudents = getValidInt(scanner, "How many students should be created? ");
        for (  int i = 0; i < numStudents; i++) {
            System.out.print("Enter name for student " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter address for student " + (i + 1) + ": ");
            String address = scanner.nextLine();
            System.out.print("Enter email for student " + (i + 1) + ": ");
            String email = scanner.nextLine();
            schoolService.addStudent(new Student(name, address, email));
        }

        System.out.println ("\n=== Setup complete for " + schoolName + " ===");
        System.out.println("Type 'HELP' to see all available commands.");


        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" "); // Splits "ENROLL S-1 C-1" into an array of 3 words

            try {
                if (input.equals("HELP")) {

                    System.out.println("Commands: ENROLL [studentId] [courseId], ASSIGN [teacherId] [courseId], SHOW COURSES, SHOW STUDENTS, SHOW TEACHERS, LOOKUP COURSE [id], LOOKUP STUDENT [id], LOOKUP TEACHER [id], SHOW PROFIT");
                }

                else if (input.startsWith("ENROLL") && parts.length == 3) {
                    schoolService.enrollStudent(parts[1], parts[2]);
                    System.out.println("Success: Student enrolled.");
                }

                else if (input.startsWith("ASSIGN") && parts.length == 3) {
                    schoolService.assignTeacher(parts[1], parts[2]);
                    System.out.println("Success: Teacher assigned.");
                }
                else if (input.equals("SHOW PROFIT")) {
                    System.out.println("Total Profit: $" + schoolService.calculateProfit());
                }
                else if (input.equals("SHOW COURSES")) {
                    schoolService.getAllCourses().forEach(c -> System.out.println("ID: " + c.getCourseId() + " | Name: " + c.getName() + " | Price: $" + c.getPrice()));
                }
                else if (input.equals("SHOW STUDENTS")) {
                    schoolService.getAllStudents().forEach(s -> System.out.println("ID: " + s.getStudentId() + " | Name: " + s.getName()));
                }
                else if (input.equals("SHOW TEACHERS")) {

                    schoolService.getAllTeachers().forEach(t -> System.out.println("ID: " + t.getTeacherId() + " | Name: " + t.getName() + " | Salary: $" + t.getSalary()));
                }
                else if (input.startsWith("LOOKUP COURSE") && parts.length == 3) {
                    Course c = schoolService.getCourseById(parts[2]);
                    if (c != null) System.out.println("Course: " + c.getName() + " | Earned: $" + c.getMoney_earned() + " | Teacher: " + (c.getTeacher() != null ? c.getTeacher().getName() : "None"));
                    else System.out.println("Course not found.");
                }

                else if (input.startsWith( "LOOKUP STUDENT") && parts.length == 3) {
                    Student s = schoolService.getStudentById(parts[2]);
                    if (s != null) System.out.println("Student: " + s.getName() + " | Email: " + s.getEmail() + " | Enrolled in: " + (s.getCourse() != null ? s.getCourse().getName() : "None"));
                    else System.out.println("Student not found.");
                }
                else if (input.startsWith("LOOKUP TEACHER")  && parts.length == 3) {
                    Teacher t = schoolService.getTeacherById(parts[2]);
                    if (t !=  null) System.out.println("Teacher: " + t.getName() + " | Salary: $" + t.getSalary());
                    else System.out.println("Teacher not found.");
                }
                else {
                    System.out.println("Command not recognized or missing IDs. Type HELP for the list.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Logic Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please check your command format.");
            }
        }
    }


    private int getValidInt(Scanner scanner, String prompt) {
        while (true )   {
            System.out.print(prompt);
            try  {
                return  Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number (e.g., 2).");
            }
        }


    }

    private double getValidDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt) ;
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException  e) {
                System.out.println("Invalid input. Please enter a valid number (e.g., 50.5).");
            }

        }
    }
}