package org.example;

import org.example.entities.Bank;
import org.example.entities.Person;
import org.hibernate.Session;

import java.util.List;

public class HibernateTask {
    private final HibernateConfig config = new HibernateConfig();

    public void getPersons() {
        try (Session session = config.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Person> result = session.createQuery("from Person", Person.class).list();
            result.forEach(p -> System.out.println("Last name: " + p.getFullName().split(" ")[0]));
            session.getTransaction().commit();
        }
    }

    public void updateBanks() {
        try (Session session = config.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Bank> banks = session.createQuery("from Bank order by id", Bank.class).list();
            updateBankName(session, banks);
            session.getTransaction().commit();
        }
        System.out.println("Banks had been updated");
    }

    private static void updateBankName(Session session, List<Bank> banks) {
        for (int i = 0; i < banks.size(); i++) {
            Bank bank = banks.get(i);
            bank.setName("Bank" + (i + 1));
            session.persist(bank);
        }
    }

}
