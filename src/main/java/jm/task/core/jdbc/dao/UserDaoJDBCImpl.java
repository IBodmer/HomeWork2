package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "user_name VARCHAR(50),last_name VARCHAR(50),age INT )");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement pst =
                     connection.prepareStatement("INSERT INTO users (user_name,last_name,age)" +
                             " VALUES (?,?,?)")) {
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setByte(3, age);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> myList = new ArrayList<>();
        try (ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                User user = new User(rs.getString("user_name"),
                        rs.getString("last_name"), rs.getByte("age"));
                myList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return myList;
    }

    public void cleanUsersTable() {
        try(Statement st = connection.createStatement()) {
            st.executeUpdate("TRUNCATE users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
