package dev.moore.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private ConnectionUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Connection createConnection(){
        try {
            return DriverManager.getConnection(System.getenv("AZURE_SQL_DB_P1"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
