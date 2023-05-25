package org.example;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTask {

    private final DataSource dataSource = JdbcConfig.createDataSource();

    public void getPersons() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM high_task.person")) {
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
        String updateName = "UPDATE high_task.bank SET name = ? where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateName)) {
            connection.setAutoCommit(false);
            for (int i = 1; i <= 4; i++) {
                statement.setString(1, "Bank" + i);
                statement.setInt(2, i);
                statement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
