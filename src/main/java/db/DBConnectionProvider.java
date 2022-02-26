package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionProvider {


    private static DBConnectionProvider Instance = new DBConnectionProvider();
    private Connection connection;


    private  String DB_URL ;
    private  String DB_USERNAME ;
    private  String DB_PASSWORD ;
    private  String DRIVER_NAME ;


    private DBConnectionProvider() {
        try {
            loadProperties();
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("C:\\Users\\DVO\\IdeaProjects\\TaskManagement1\\src\\main\\resources\\config.properties"));
        DRIVER_NAME = properties.getProperty("db.driver.name");
        DB_URL = properties.getProperty("db.url");
        DB_USERNAME = properties.getProperty("db.username");
        DB_PASSWORD = properties.getProperty("db.password");

    }

    public static DBConnectionProvider getInstance() {
        return Instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return connection;

    }
}
