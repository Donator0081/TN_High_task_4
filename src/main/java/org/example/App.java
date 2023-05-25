package org.example;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        JdbcTask jdbcTask = new JdbcTask();
        jdbcTask.getPersons();
        jdbcTask.updateBanks();
    }
}
