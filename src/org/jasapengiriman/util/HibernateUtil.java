package org.jasapengiriman.util;


import org.hibernate.Session; 
import org.hibernate.SessionFactory; 
import org.hibernate.boot.registry.StandardServiceRegistryBuilder; 
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Polma E. Tambunan
 */
public class HibernateUtil 
{
    private static final SessionFactory SESSION_FACORY = createSessionFactory();;
    private static ThreadLocal<Session> session = new ThreadLocal<Session>();
    
    public static SessionFactory createSessionFactory()
    {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        return configuration.buildSessionFactory(new 
        StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());  
    }
    public static Session getSession()
    {
        session.set(SESSION_FACORY.openSession());
        return session.get();
    }
    
    public static void closeSession()
    {
        Session s = session.get();
        if (s != null)
        {
            s.close();
            session.remove();
        }
    }
}
