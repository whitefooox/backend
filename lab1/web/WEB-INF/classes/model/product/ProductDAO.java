package model.product;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.utils.DBUtil;

public class ProductDAO {
    public static ArrayList<Product> getAll(String login) throws Exception{
        ArrayList<Product> list = new ArrayList<>();
        ResultSet resultSet = DBUtil.dbExecuteQuery("select products.id, products.\"name\", products.count, products.user_id from accounts, products where accounts.id = products.user_id and accounts.login = '" + login + "'");
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            int user_id = resultSet.getInt("user_id");
            String name = resultSet.getString("name");
            int count = resultSet.getInt("count");
            Product product = new Product(user_id, name, count, id);
            list.add(product);
        }
        return list;
    }
}
