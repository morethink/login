package studio.geek.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/library?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    public static Connection connection = null;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return connection;
    }
}
