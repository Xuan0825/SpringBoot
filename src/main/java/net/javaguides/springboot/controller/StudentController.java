package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
   private List<Student> students;
   // just for demo in real life we use repository and service
    // and using dependency injection to interact with the database;

    public StudentController() {
        students = new ArrayList<>();
        students.add(new Student("Name0","Lei",0));
        students.add(new Student("Name1","Park",1));
        students.add(new Student("Name2","Parker",2));
    }

    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student newStudent = new Student(
                "Peter",
                "Wong",'3'
        );
       // return new ResponseEntity<>(newStudent,HttpStatus.OK)
       // return ResponseEntity.ok(newStudent);
        return ResponseEntity.ok().header(
                "some-header",
                "header-type"
        ).body(newStudent);
    }
    @GetMapping("students")
    public List<Student> getStudents(){
        return students;
    }

    @GetMapping("students/{id}")// Rest Api with path
    // {id} parameter for the query
    public Student getSingleStudent(@PathVariable int id
    ){
        // you can do @PathVariable int id or
        // use @PathVariable("id") so that you can replace id to student id
        // need to @PathVariable to indicate the id as the parameter to query the list
        return students.get(id);
    }
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student queryStudent(@PathVariable("id") int studentId,
                                @PathVariable("first-name") String firstName,
                                @PathVariable("last-name") String lastName){
        return new Student(firstName,lastName,studentId);

    }

    @GetMapping("students/query") //http://localhost:8080/students/query?id=1
    public Student requestStudent(@RequestParam int id){
        return students.get(id);
    }

    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createNewStudent(@RequestBody Student student){
        students.add(student);
      //  return new ResponseEntity<>(student,HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
        // either way is ok
    }

    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student,@PathVariable int id){
       Student originalStudent = students.get(id);
       originalStudent.setId(student.getId());
       originalStudent.setFirstName(student.getFirstName());
       originalStudent.setLastName(student.getLastName());
       return originalStudent;
    }

    @DeleteMapping("students/{id}/delete")
    public Student deleteStudent(@PathVariable int id){
        Student deleteItem = students.get(id);
        students.remove(id);
        return deleteItem;
    }

}
