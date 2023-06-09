package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTask {

    public static final String SELECT_FROM_PERSON = "SELECT * FROM high_task.person";
    public static final String UPDATE_BANK_NAME = "UPDATE high_task.bank SET name = ? where id = ?";
    public static final String SELECT_COUNT_BANK = "SELECT COUNT(*) as count FROM high_task.bank";
    private final Connection connection = JdbcConfig.createConnection();

    public void getPersons() {
        try (var statement = connection.prepareStatement(SELECT_FROM_PERSON)) {
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String lastName = resultSet.getString("full_name").split(" ")[0];
                System.out.println("Person with id "
                        + resultSet.getInt("id")
                        + " with last name "
                        + lastName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBanks() {
        try (var updatePS = connection.prepareStatement(UPDATE_BANK_NAME);
             var selectPS = connection.prepareStatement(SELECT_COUNT_BANK)) {
            connection.setAutoCommit(false);
            ResultSet resultSet = selectPS.executeQuery();
            resultSet.next();
            int count = resultSet.getInt("count");
            updateBankName(updatePS, count);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Banks had been updated");
    }

    private static void updateBankName(PreparedStatement updatePS, int count) throws SQLException {
        for (int i = 1; i <= count; i++) {
            updatePS.setString(1, "Bank" + i);
            updatePS.setInt(2, i);
            updatePS.executeUpdate();
        }
    }
}
