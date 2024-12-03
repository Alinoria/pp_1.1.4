package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    static final String URL = "jdbc:mysql://localhost:3306/my_db";
    static final String USER = "root";
    static final String PASSWORD = "jpacourse";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Успешное подключение!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Не получилось подключиться: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/my_db");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "jpacourse");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");

            configuration.addAnnotatedClass(User.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to initialize Hibernate");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
