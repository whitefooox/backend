package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.data.entity.User;
import model.data.repository.IUserRepository;

public class SqlUserRepository implements IUserRepository {

    private Connection connection;

    public SqlUserRepository(){
        this.connection = DataBase.getConnection();
    }

    @Override
    public void add(User user) {
        
    }

    @Override
    public boolean check(User user) {
        String query = "select * from accounts where login = ? and password = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
