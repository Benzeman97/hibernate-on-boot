package com.benz.hib.jpa.api.dao.impl;

import com.benz.hib.jpa.api.dao.StudentDao;
import com.benz.hib.jpa.api.entity.Student;
import com.benz.hib.jpa.api.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDaoImpl implements StudentDao {

    private HibernateUtil hibernateUtil;

    public StudentDaoImpl(HibernateUtil hibernateUtil)
    {
        this.hibernateUtil=hibernateUtil;
    }


    @Override
    public Optional<List<Student>> getStudents() {
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try{
            List<Student> students = session.createQuery("from Student").list();
            transaction.commit();
            return Optional.of(students);


    @Transactional
    @Override
    public Optional<List<Student>> getStudents() {
        Session session = hibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();

         try{
            List<Student> students = session.createQuery("from Student").list();
            transaction.commit();
             return Optional.of(students);

        }catch (Exception ex)
        {
            transaction.rollback();
            return null;
        }

    }

    @Override
    public Student findStudent(int stuId) {
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("from Student s where s.stuId= :stuId");
            query.setParameter("stuId", stuId);
            List<Student> students = query.list();
            transaction.commit();
            if (students.size() == 0)
                return null;
            return students.get(0);
        }catch (Exception ex)
        {
            transaction.rollback();
            return null;
        }

    }

    @Override
    public Student saveStudent(Student student) {
        Session session = hibernateUtil.getSession();
        Transaction transaction= session.beginTransaction();
        try {
            session.save(student);
            transaction.commit();
            return student;
        }catch (Exception ex)
        {
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Student updateStudent(Student student) {
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(student);
            transaction.commit();
            return student;
        }catch (Exception ex)
        {
            transaction.rollback();
            return null;
        }

      Transaction transaction = session.beginTransaction();
      try {
          session.saveOrUpdate(student);
          transaction.commit();
          return student;
      }catch (Exception ex)
      {
          transaction.rollback();
          return null;
      }

    }

    @Override
    public void deleteStudent(Student student) {
        Session session = hibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(student);
            transaction.commit();
        }catch (Exception ex)
        {
            transaction.commit();
        }
    }

}
