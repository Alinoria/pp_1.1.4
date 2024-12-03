package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args)  {
       // UserDao userDao = new UserDaoJDBCImpl();
       // userDao.createUsersTable();
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();

       // userDao.saveUser("Иван", "Иванов", (byte) 20);
       // userDao.saveUser("Антон", "Фадеев", (byte) 25);
        //userDao.saveUser("Михаил", "Кузнецов", (byte) 31);
       // userDao.saveUser("Сергей", "Гребенкин", (byte) 38);

        userDaoHibernate.saveUser("Алиса", "Васильева", (byte) 19);
        userDaoHibernate.saveUser("Мария", "Медведева", (byte) 31);
        userDaoHibernate.saveUser("Наталья", "Попова", (byte) 22);
        userDaoHibernate.saveUser("Ольга", "Петрова", (byte) 29);

       // userDao.removeUserById(7);
       // userDao.getAllUsers();
       // userDao.cleanUsersTable();
        //userDao.dropUsersTable();



        userDaoHibernate.removeUserById(4);
        userDaoHibernate.getAllUsers();
      //  userDaoHibernate.cleanUsersTable();
      //  userDaoHibernate.dropUsersTable();

    }
}
