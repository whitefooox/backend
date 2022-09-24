package model.user;
import java.sql.ResultSet;
import model.utils.DBUtil;

public class UserDAO {
    public static boolean validate(String login, String password) throws Exception{
        ResultSet resultSet = DBUtil.dbExecuteQuery("select * from accounts where login = '" + login + "' and password = '" + password + "'");
        return resultSet.next();
    }

    public static int getId(String login, String password) throws Exception{
        ResultSet resultSet = DBUtil.dbExecuteQuery("select * from accounts where login = '" + login + "' and password = '" + password + "'");
        resultSet.next();
        return resultSet.getInt("id");
    }
}
