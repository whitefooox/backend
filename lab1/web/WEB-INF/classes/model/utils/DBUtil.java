package model.utils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class DBUtil {

    private static String JDBC_DRIVER = "org.postgresql.Driver";
    private static String connString = "jdbc:postgresql://localhost:5432/web";
    private static String user = "postgres";
    private static String password = "antimagus";

    private static Connection connection = null;

    private static void dbConnect() throws SQLException, ClassNotFoundException, IOException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw e;
        }
        try {
            connection = DriverManager.getConnection(connString, user, password);
        } catch (SQLException e) {
            throw e;
        }
    }

    private static void dbDisconnect() throws SQLException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public static ResultSet dbExecuteQuery(String query) throws Exception {
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSet crs;
        try {
            dbConnect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } 
        finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            dbDisconnect();
        }
        return crs;
    }

    public static void dbExecuteUpdate(String update) throws Exception {
        Statement statement = null;
        try {
            dbConnect();
            statement = connection.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            dbDisconnect();
        }
    }
}