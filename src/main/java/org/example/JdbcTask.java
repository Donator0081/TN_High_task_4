package org.example;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTask {

    public static final String SELECT_FROM_PERSON = "SELECT * FROM high_task.person";
    public static final String UPDATE_BANK_NAME = "UPDATE high_task.bank SET name = ? where id = ?";
    public static final String SELECT_COUNT_BANK = "SELECT COUNT(*) as count FROM high_task.bank";
    private final DataSource dataSource = JdbcConfig.createDataSource();

    public void getPersons() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FROM_PERSON)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Person with id "
                        + resultSet.getInt("id")
                        + " with name "
                        + resultSet.getString("full_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBanks() {
        String updateName = UPDATE_BANK_NAME;
        String selectBanks = SELECT_COUNT_BANK;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement updatePS = connection.prepareStatement(updateName);
             PreparedStatement selectPS = connection.prepareStatement(selectBanks)) {
            connection.setAutoCommit(false);
            ResultSet resultSet = selectPS.executeQuery();
            resultSet.next();
            int count = resultSet.getInt("count");;
            for (int i = 1; i <= count; i++) {
                updatePS.setString(1, "Bank" + i);
                updatePS.setInt(2, i);
                updatePS.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
