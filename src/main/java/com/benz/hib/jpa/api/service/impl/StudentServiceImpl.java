package com.benz.hib.jpa.api.service.impl;

import com.benz.hib.jpa.api.dao.StudentDao;
import com.benz.hib.jpa.api.entity.Student;
import com.benz.hib.jpa.api.exception.DataNotFoundException;
import com.benz.hib.jpa.api.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao)
    {
        this.studentDao=studentDao;
    }

    @Override
    public List<Student> getStudents() {
        List<Student> students = studentDao.getStudents().get();

        if(students.size()==0)
            throw new  DataNotFoundException("No data available in the database");
       return students;
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {

        Student stu =studentDao.findStudent(student.getStuId());
        if(Objects.isNull(stu))
          return studentDao.saveStudent(student);
        throw new DataNotFoundException(String.format("Student is existed with %d",student.getStuId()));
    }

    @Override
    public Student updateStudent(int stuId, Student student) {
        Student stu =studentDao.findStudent(stuId);
        if(Objects.isNull(stu))
            throw new DataNotFoundException(String.format("Student is not found with %d", stuId));
        stu.setStuName(student.getStuName());
        stu.setSalary(student.getSalary());
        return studentDao.updateStudent(stu);
    }

    @Override
    public Student findStudent(int stuId) {

        Student student = studentDao.findStudent(stuId);
        if(Objects.isNull(student))
           throw new DataNotFoundException(String.format("Student is not found with %d", stuId));
        return student;
    }

    @Override
    public void deleteStudent(int stuId) {
        Student student = studentDao.findStudent(stuId);
        if(Objects.isNull(student))
            throw new DataNotFoundException(String.format("Student is not found with %d", stuId));
          studentDao.deleteStudent(student);
    }
}
