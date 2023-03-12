package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
//        userDaoJDBC.createUsersTable();
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
//        userDaoJDBC.dropUsersTable();
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
//        userDaoJDBC.saveUser(name, lastName, age);
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
//        userDaoJDBC.removeUserById(id);
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
//        List<User> allUsers = userDaoJDBC.getAllUsers();
        List<User> allUsers = userDaoHibernate.getAllUsers();
        allUsers.forEach(System.out::println);
        return allUsers;
    }

    public void cleanUsersTable() {
//        userDaoJDBC.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();
    }
}
