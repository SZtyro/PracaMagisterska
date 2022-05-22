package pl.sztyro.pracamagisterska.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@Configuration
@Service
@EnableTransactionManagement
@EnableAutoConfiguration
public class HibernateUtil {

    //    @PersistenceContext
//    private EntityManager entityManager;
//    static org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration()
//            .configure();;

    public HibernateUtil() {

    }

    static private SessionFactory hibernateFactory;

//    @Autowired
//    public HibernateUtil(EntityManagerFactory factory) {
//        if(factory.unwrap(SessionFactory.class) == null){
//            throw new NullPointerException("factory is not a hibernate factory");
//        }
//        this.hibernateFactory = factory.unwrap(SessionFactory.class);
//    }
    //static SessionFactory sessionFactory;

    /**
     * Zwraca otwartą sesję hibernate
     *
     * @return Session
     */
//    public Session getSession() {
//        Session session = null;
//        if (entityManager == null || (session = entityManager.unwrap(Session.class)) == null) {
//
//            throw new NullPointerException();
//        }
//        //session.beginTransaction();
//
//        return session;
//    }
     static public Session getSession() {
        return hibernateFactory.openSession();
    }

//    private static final SessionFactory sessionFactory = buildSessionFactory();
//
//    private static SessionFactory buildSessionFactory()
//    {
//        try
//        {
//            // Create the SessionFactory from hibernate.cfg.xml
//            return new AnnotationConfiguration().configure(new File("hibernate.cgf.xml")).buildSessionFactory();
//        }
//        catch (Throwable ex) {
//            // Make sure you log the exception, as it might be swallowed
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
}