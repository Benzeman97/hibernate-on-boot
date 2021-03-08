package com.benz.hib.jpa.api.service;

import com.benz.hib.jpa.api.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("transactionManager")
public interface StudentService {

    List<Student> getStudents();

    Student saveStudent(Student student);

    Student updateStudent(int studId,Student student);

    Student findStudent(int stuId);

    void deleteStudent(int stuId);
}
