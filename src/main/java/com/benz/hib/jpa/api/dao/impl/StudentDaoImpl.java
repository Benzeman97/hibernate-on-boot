package com.benz.hib.jpa.api.dao.impl;

import com.benz.hib.jpa.api.dao.StudentDao;
import com.benz.hib.jpa.api.entity.Student;
import com.benz.hib.jpa.api.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDaoImpl implements StudentDao {


    private HibernateUtil hibernateUtil;
    private SessionFactory sessionFactory;

    public StudentDaoImpl(HibernateUtil hibernateUtil,SessionFactory sessionFactory)
    {
        this.hibernateUtil=hibernateUtil;
        this.sessionFactory=sessionFactory;
    }


    @Override
    public Optional<List<Student>> getStudents() {
        Session session = sessionFactory.getCurrentSession();
        List<Student> students = session.createQuery("from Student").list();
        return Optional.of(students);
    }

    @Override
    public Student findStudent(int stuId) {
        Session session=  sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Student s where s.stuId= :stuId");
        query.setParameter("stuId",stuId);
        List<Student> students  = query.list();
          if(students.size()==0)
              return null;
          return students.get(0);

    }

    @Override
    public Student saveStudent(Student student) {
        Session session =  hibernateUtil.getSession();
        session.save(student);
        session.flush();
        session.close();
        return student;
    }

    @Override
    public Optional<Student> updateStudent(Student student) {
        Session session=  hibernateUtil.getSession();
        session.persist(student);
        return Optional.of(student);
    }

    @Override
    public void deleteStudent(Student student) {
        Session session= hibernateUtil.getSession();
        session.delete(student);
    }
}
