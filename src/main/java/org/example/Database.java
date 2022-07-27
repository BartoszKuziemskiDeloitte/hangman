package org.example;

import java.sql.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Database {

    private static Connection connection;
    private static final String DB_NAME = "jdbc-video";
    private static final String DB_TABLE_NAME = "passwords";
    private static final String DB_COLUMN_NAME = "password";

    private static void setConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-video?serverTimezone=GMT&useSSL=false",
                "root",
                "root");
    }

    private static int countDatabaseRows() {
        String sql = "select count(*) from " + DB_TABLE_NAME;
        try {
            setConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count(*)");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static String getPassword() {
        Random random = new Random();
        int dbRowsNumber = countDatabaseRows();
        if (dbRowsNumber == 0) {
            return null;
        }
        int passwordId = random.nextInt(dbRowsNumber) + 1; // range (1 : rowsNumber)
        try {
            setConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + DB_TABLE_NAME + " where id = " + passwordId);

            if (resultSet.next()) {
                return resultSet.getString(DB_COLUMN_NAME);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static boolean checkIfContainLettersOnly(String password) {
        // return password.chars().allMatch(Character::isLetter);
        Pattern p = Pattern.compile("^[ A-Za-z]+$");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static boolean addPassword(String password) {
        if (!checkIfContainLettersOnly(password)) {
            return false;
        }
        ResultSet resultSet = null;
        int passwordId = 0;
        // INSERT INTO `db-name`.`table` (password) VALUES('text');
        String sql = "INSERT INTO " + "`" + DB_NAME + "`" + "." + "`" + DB_TABLE_NAME + "`" + "(" + DB_COLUMN_NAME + ")" + "VALUES(?)";
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

    public static void deleteAllPasswords() {
        String sql = "TRUNCATE " + "`" + DB_NAME + "`.`" + DB_TABLE_NAME + "`";
        try {
            setConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
