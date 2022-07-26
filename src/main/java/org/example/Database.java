package org.example;

import java.sql.*;
import java.util.Random;

public class Database {

    private static Connection connection;
    private static final String DB_NAME = "jdbc-video";
    private static final String TABLE_NAME = "passwords";
    private static final String COLUMN_NAME = "password";

    private static void setConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-video?serverTimezone=GMT&useSSL=false",
                "root",
                "root");
    }

    public static String getPassword() {
        Random random = new Random();
        int passwordId = random.nextInt(3) + 1; // TODO: 25.07.2022 get table size
        try {
            setConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + TABLE_NAME + " where id = " + passwordId);

            if (resultSet.next()) {
                return resultSet.getString(COLUMN_NAME);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static boolean addPassword(String password) {
        ResultSet resultSet = null;
        int passwordId = 0;
        // INSERT INTO `db-name`.`table` (password) VALUES('text');
        String sql = "INSERT INTO " + "`" + DB_NAME + "`" + "." + "`" + TABLE_NAME + "`" + "(" + COLUMN_NAME + ")" + "VALUES(?)";
        try {
            setConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, password);

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 1) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    passwordId = resultSet.getInt(1);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return passwordId > 0;
    }

    // TODO: 26.07.2022 delete all records // TRUNCATE `jdbc-video`.`passwords`;
}
