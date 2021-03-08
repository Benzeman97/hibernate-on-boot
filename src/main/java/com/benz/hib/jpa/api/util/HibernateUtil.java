package com.benz.hib.jpa.api.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {

    private ThreadLocal<Session> thlSession = new ThreadLocal<>();

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateUtil(SessionFactory sessionFactory)
    {
         this.sessionFactory = sessionFactory;
    }

    public Session getSession()
    {
        Session session=null;

        if(thlSession.get()==null) {
            session = sessionFactory.openSession();
            thlSession.set(session);
        }else
            session = thlSession.get();
        return session;
    }

    public void closeSession()
    {
        Session session=null;

        if(thlSession.get()!=null)
        {
            session = thlSession.get();
            session.close();
            thlSession.remove();
        }
    }

    public void closeSessionFactory()
    {
        sessionFactory.close();
    }
}
