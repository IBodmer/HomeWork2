package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    public static Connection getConnectionJDBC() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/homeWorkDB", "postgres", "11235813");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static SessionFactory getConnection() {

        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/homeWorkDB")
                    .setProperty("hibernate.connection.username", "postgres")
                    .setProperty("hibernate.connection.password", "11235813")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect")
                    .setProperty("hibernate.current_session_context_class","thread")
                    .addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
