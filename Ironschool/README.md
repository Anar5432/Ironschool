# IronSchool Project 🚀

hey guys! this is the repo for the ironschool project. me and the team built this for the java course. 
its a spring boot api for managing our next big edtech startup (well right now just a MVP lol)

## how to run / demo

1. clone it 
2. open in intellij or whatever u use
3. run `IronschoolApplication.java`
(or just do `./mvnw spring-boot:run` in terminal, kinda easier)

we tested it with postman and it works on `localhost:8080` (mostly). 

## the tech / how it works
for the examiners/reviewers, here is what we actually built inside:

**models we got:**
- `Student`: holds student info
- `Teacher`: holds teacher info & salary
- `Course`: has the course details, price, and tracks how much `money_earned`

**data storage:**
tbh we didn't add a real database yet so it's all running on in-memory `HashMap`s inside the `SchoolService`. just run the app and hit the endpoints to test, but remember if you restart the app the data goes

**what the API does:**
we have a `SchoolController` that handles these GET endpoints:
- `GET /students` -> spits out all students
- `GET /courses` -> shows all courses and their stats
- `GET /teachers` -> lists the teachers
- `GET /profit` -> this is the big one for the startup! calculates `total revenue (from enrolled students) - total salaries (from teachers)`

the `SchoolService` has the methods to `addStudent`, `addTeacher`, `addCourse`, `enrollStudent` (which automatically updates the course revenue!), and `assignTeacher`. 

if something crashes during your review just try to restart it lol 

✌️
