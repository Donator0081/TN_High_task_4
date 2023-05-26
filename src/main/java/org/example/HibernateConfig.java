package org.example;

import org.example.entities.Bank;
import org.example.entities.Person;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

public class HibernateConfig {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(DRIVER, "org.postgresql.Driver");
                settings.put(URL, "jdbc:postgresql://localhost:5432/postgres");
                settings.put(USER, "postgres");
                settings.put(PASS, "postgres");
                settings.put(DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(SHOW_SQL, "true");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Bank.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
