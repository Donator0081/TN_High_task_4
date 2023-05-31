package org.example;

import org.example.entities.Bank;
import org.example.entities.Person;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.DIALECT;
import static org.hibernate.cfg.AvailableSettings.DRIVER;
import static org.hibernate.cfg.AvailableSettings.PASS;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;
import static org.hibernate.cfg.AvailableSettings.URL;
import static org.hibernate.cfg.AvailableSettings.USER;

public class HibernateConfig {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                var configuration = new Configuration();
                var settings = new Properties();

                settings.put(DRIVER, "org.postgresql.Driver");
                settings.put(URL, "jdbc:postgresql://localhost:5432/postgres");
                settings.put(USER, "postgres");
                settings.put(PASS, "postgres");
                settings.put(DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(SHOW_SQL, "true");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Bank.class);

                var serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
