package com.benz.hib.jpa.api.controller;

import com.benz.hib.jpa.api.entity.Student;
import com.benz.hib.jpa.api.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService)
    {
        this.studentService=studentService;
    }

    @GetMapping(value = "/{stuId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Student> findStudent(@PathVariable("stuId") int studentId)
    {
        return studentId!=0 ?
            new ResponseEntity<>(studentService.findStudent(studentId), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Student>> getStudents()
    {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping(value = "/save",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Student> saveStudent(@RequestBody Student student)
    {
        if(student.getStuId()!=0 && !student.getStuName().trim().isEmpty() && student.getSalary()!=0.0)
            return new ResponseEntity<>(studentService.saveStudent(student),HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{stuId}/update",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Student> updateStudent(@PathVariable int stuId,@RequestBody Student student)
    {
        if(stuId!=0 && !student.getStuName().trim().isEmpty() && student.getSalary()!=0.0)
            return new ResponseEntity<>(studentService.updateStudent(stuId,student),HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{stuId}")
    public void delete(@PathVariable int stuId)
    {
        if(stuId==0)
          throw new IllegalArgumentException();
        studentService.deleteStudent(stuId);
    }
}
