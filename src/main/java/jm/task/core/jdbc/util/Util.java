package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    static final String URL = "jdbc:mysql://localhost:3306/my_db";
    static final String USER = "root";
    static final String PASSWORD = "jpacourse";

    public static Connection getConnection() {
        try{Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Успешное подключение!");
            return connection;}

        catch (SQLException e){
            System.out.println("Не получилось подключиться");
            throw new RuntimeException(e);
        }

    }

}
