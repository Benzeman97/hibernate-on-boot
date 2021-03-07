package com.benz.hib.jpa.api.service;

import com.benz.hib.jpa.api.entity.Student;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentService {

    List<Student> getStudents();

    @Transactional(propagation = Propagation.REQUIRED)
    Student saveStudent(Student student);

    Student updateStudent(int studId,Student student);

    Student findStudent(int stuId);

    void deleteStudent(int stuId);
}
