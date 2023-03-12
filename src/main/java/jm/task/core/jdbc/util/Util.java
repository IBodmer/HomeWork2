package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    public static Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/homeWorkDB", "postgres", "11235813");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

}
