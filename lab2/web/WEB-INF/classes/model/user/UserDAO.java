package model.user;
import java.sql.ResultSet;
import model.util.DBUtil;

public class UserDAO {
    public static boolean validate(String login, String password) throws Exception{
        ResultSet resultSet = DBUtil.dbExecuteQuery("select * from accounts where login = '" + login + "' and password = '" + password + "'");
        return resultSet.next();
    }

    public static int getId(String login) throws Exception{
        ResultSet resultSet = DBUtil.dbExecuteQuery("select * from accounts where login = '" + login + "'");
        resultSet.next();
        return resultSet.getInt("id");
    }
}