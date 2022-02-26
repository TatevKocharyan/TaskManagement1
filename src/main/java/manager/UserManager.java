package manager;

import db.DBConnectionProvider;
import model.User;
import model.UserType;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private User user = new User();

    public void add(User user) {
        String sql = "INSERT INTO user(`name`,surname,email,password,`type` ) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(4, user.getUserType().name());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }


    }

    public User getUserByEmailAndPassword(String email, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user where email=? AND password=?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUserType(UserType.valueOf(resultSet.getString("type")));
                return user;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    private User getUserFromResultSet(ResultSet resultSet) {

        try {
            return User.builder()
                    .id(resultSet.getLong(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .password(resultSet.getString(5))
                    .userType(UserType.valueOf(resultSet.getString(6)))
                    .build();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }


    public User getByid(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE id=?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUserType(UserType.valueOf(resultSet.getString("type")));
                return user;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;
    }



        public List<User> getAllUsers(){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT  * FROM user");
                List<User> users = new LinkedList<>();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setUserType(UserType.valueOf(resultSet.getString("type")));
                    return users;
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }


            return null;
        }


}
