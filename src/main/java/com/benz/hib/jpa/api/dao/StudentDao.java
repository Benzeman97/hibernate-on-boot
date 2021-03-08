package com.benz.hib.jpa.api.dao;

import com.benz.hib.jpa.api.entity.Student;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

    Optional<List<Student>> getStudents();

     Student findStudent(int stuId);

    Student saveStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(Student student);

}
