package manager;

import db.DBConnectionProvider;
import model.Task;
import model.Task ;
import model.TaskStatusType;
import model.UserType;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class TaskManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private Task task = new Task();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    UserManager userManager=new UserManager();

    public void add(Task task) {
        String sql = "INSERT INTO user(name,description ,deadline,status,user_id ) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, sdf.format(task.getDeadline()));
            preparedStatement.setString(4, task.getTaskStatusType().name());
            preparedStatement.setInt(4, task.getUserId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id=resultSet.getInt(1);
                task.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();


        }


    }



    public List<Task> getAllTasks() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM task");
            List<Task> tasks = new LinkedList<>();
            while (resultSet.next()) {
                Task  task = new Task ();
                task.setId(resultSet.getLong("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadline(sdf.parse(resultSet.getString("deadline")));
                task.setTaskStatusType(TaskStatusType.valueOf(resultSet.getString("password")));
                task.setUserId(resultSet.getInt("user_id"));
                task.setUser(userManager.getByid(task.getUserId()));
                tasks.add(task);

            }
            return tasks;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }

    public List<Task> getAllTasksByUserId(int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM task WHERE user_id=?");
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Task> tasks = new LinkedList<>();
            while (resultSet.next()) {
                Task  task = new Task ();
                task.setId(resultSet.getLong("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadline(sdf.parse(resultSet.getString("deadline")));
                task.setTaskStatusType(TaskStatusType.valueOf(resultSet.getString("password")));
                task.setUserId(resultSet.getInt("user_id"));
                task.setUser(userManager.getByid(task.getUserId()));
                tasks.add(task);

            }
            return tasks;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }



}
