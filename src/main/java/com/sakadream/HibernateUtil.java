package com.sakadream;

import com.sakadream.models.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Properties;

/**
 * Created by Phan Ba Hai on 25/08/2017.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Properties prop = new Properties();
            prop.put("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            prop.put("hibernate.connection.url", "jdbc:sqlserver://sakadream-ws-test.database.windows.net:1433;database=WebServiceTest");
            prop.put("hibernate.connection.username", "ry9CAL3W");
            prop.put("hibernate.connection.password", "whH!nBZP4g%^5xu2");
            prop.put("hibernate.current_session_context_class", "thread");
            prop.put("hibernate.query.factory_class", "org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory");
            prop.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
            prop.put("hibernate.show_sql", "true");

            sessionFactory = new AnnotationConfiguration()
                    .addPackage("com.sakadream.models")
                    .addProperties(prop)
                    .addAnnotatedClass(Employee.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
