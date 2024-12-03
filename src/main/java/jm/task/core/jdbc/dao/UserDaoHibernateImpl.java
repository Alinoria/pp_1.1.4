package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
public UserDaoHibernateImpl() {
}

@Override
public void createUsersTable() {
    String sql = "CREATE TABLE IF NOT EXISTS user_db (" +
            "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(255), " +
            "lastName VARCHAR(255), " +
            "age TINYINT)";

    try (Session session = Util.getSessionFactory().openSession()) {
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
    } catch (Exception e) {
        System.err.println("Ошибка создания таблицы: " + e.getMessage());
        e.printStackTrace();
    }
}

@Override
public void dropUsersTable() {
    String sql = "DROP TABLE IF EXISTS user_db";

    try (Session session = Util.getSessionFactory().openSession()) {
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
    } catch (Exception e) {
        System.err.println("Ошибка удаления таблицы: " + e.getMessage());
        e.printStackTrace();
    }
}

@Override
public void saveUser(String name, String lastName, byte age) {
    try (Session session = Util.getSessionFactory().openSession()) {
        session.beginTransaction();
        User user = new User(name, lastName, age);  // создаем новый объект пользователя
        session.save(user);  // сохраняем пользователя в базу данных
        session.getTransaction().commit();
    } catch (Exception e) {
        System.err.println("Ошибка сохранения пользователя: " + e.getMessage());
        e.printStackTrace();
    }
}

@Override
public void removeUserById(long id) {
    try (Session session = Util.getSessionFactory().openSession()) {
        session.beginTransaction();
        User user = session.get(User.class, id);  // находим пользователя по ID
        if (user != null) {
            session.delete(user);  // удаляем пользователя
        }
        session.getTransaction().commit();
    } catch (Exception e) {
        System.err.println("Ошибка удаления пользователя: " + e.getMessage());
        e.printStackTrace();
    }
}

@Override
public List<User> getAllUsers() {
    try (Session session = Util.getSessionFactory().openSession()) {
        session.beginTransaction();
        List<User> users = session.createQuery("FROM User", User.class).getResultList();  // выбираем всех пользователей
        session.getTransaction().commit();
        return users;
    } catch (Exception e) {
        System.err.println("Ошибка получения пользователей: " + e.getMessage());
        e.printStackTrace();
        return new ArrayList<>();
    }
}

@Override
public void cleanUsersTable() {
    String sql = "DELETE FROM user_db";

    try (Session session = Util.getSessionFactory().openSession()) {
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
    } catch (Exception e) {
        System.err.println("Ошибка очистки таблицы: " + e.getMessage());
        e.printStackTrace();
    }
}
}

