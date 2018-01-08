package com.sakadream;

import com.sakadream.models.Employee;

import org.apache.catalina.core.StandardService;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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
            prop.put("hibernate.connection.url", System.getenv("DB_URL"));
            prop.put("hibernate.connection.username", System.getenv("DB_USERNAME"));
            prop.put("hibernate.connection.password", System.getenv("DB_PASSWORD"));
            prop.put("hibernate.current_session_context_class", "thread");
            prop.put("hibernate.query.factory_class", "org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory");
            prop.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
            prop.put("hibernate.show_sql", "true");

            Configuration config = new Configuration()
                .addPackage("com.sakadream.models")
                .addProperties(prop)
                .addAnnotatedClass(Employee.class)
                .configure();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties())
                .build();

            sessionFactory = config.buildSessionFactory(serviceRegistry);
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
