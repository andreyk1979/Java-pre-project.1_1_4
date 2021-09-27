package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String DB_LOGIN = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
            //System.out.println("Соеднинение с БД установленно ");
        } catch (SQLException e) {
            System.err.println("Соеднинение с БД НЕ установленно");
            e.printStackTrace();
        }
        return connection;
    }
}


