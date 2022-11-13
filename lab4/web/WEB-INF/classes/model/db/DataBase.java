package model.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
    
    private static String JDBC_DRIVER = "org.postgresql.Driver";
    private static String connString = "jdbc:postgresql://localhost:5432/app";
    private static String user = "postgres";
    private static String password = "postgres";
    private static Connection connection = null;

    public static synchronized Connection getConnection() {
		if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(connString, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
		return connection;
	}
}
