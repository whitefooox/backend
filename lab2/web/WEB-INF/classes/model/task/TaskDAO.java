package model.task;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.util.DBUtil;

public class TaskDAO {
    
    public static void insert(Task task) throws Exception{
        DBUtil.dbExecuteUpdate("insert into task (userId, description, status) values (" + String.valueOf(task.getUserId()) + ", '" + task.getDescription() + "', '" + task.getStatus() + "')");
    }

    public static List<Task> getAll(int userId) throws Exception{
        List<Task> tasks = new ArrayList<>();
        ResultSet resultSet = DBUtil.dbExecuteQuery("select * from task where userId = " + userId);
        while(resultSet.next()){
            Task task = new Task();
            task.setId(resultSet.getInt("id"));
            task.setUserId(resultSet.getInt("userId"));
            task.setDescription(resultSet.getString("description"));
            task.setStatus(resultSet.getString("status"));
            tasks.add(task);
        }
        return tasks;
    }

    public static void updateState(int id, String state) throws Exception {
        DBUtil.dbExecuteUpdate("update task set status = '" + state + "' where id = " + id);
    }

    public static void delete(int id) throws Exception{
        DBUtil.dbExecuteUpdate("delete from task where id = " + id);
    }
}
