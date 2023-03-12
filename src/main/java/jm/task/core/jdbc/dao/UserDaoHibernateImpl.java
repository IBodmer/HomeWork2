package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sf = Util.getConnection();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session currentSession = sf.getCurrentSession();
        Transaction transaction = null;
        try (currentSession) {
            transaction = currentSession.beginTransaction();
            currentSession.createNativeQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "user_name VARCHAR(50),last_name VARCHAR(50),age INT )").executeUpdate();
            transaction.commit();
            System.out.println("таблица создалась");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Session currentSession = sf.getCurrentSession();
        Transaction transaction = null;
        try (currentSession) {
            transaction = currentSession.beginTransaction();
            currentSession.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            System.out.println("таблицу дропнули");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session currentSession = sf.getCurrentSession();
        Transaction transaction = null;
        try (currentSession) {
            transaction = currentSession.beginTransaction();
            currentSession.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session currentSession = sf.getCurrentSession();
        Transaction transaction = null;
        try (currentSession) {
            transaction = currentSession.beginTransaction();
            currentSession.delete(currentSession.get(User.class, id));
            transaction.commit();
            System.out.println("Юзер удален");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session currentSession = sf.getCurrentSession();
        Transaction transaction = null;
        List<User> list = null;
        try (currentSession) {
            transaction = currentSession.beginTransaction();
            list = currentSession.createNativeQuery("SELECT * FROM users", User.class).getResultList();
            transaction.commit();
            return list;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            if (transaction != null) transaction.rollback();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session currentSession = sf.getCurrentSession();
        Transaction transaction = null;
        try (currentSession) {
            transaction = currentSession.beginTransaction();
            currentSession.createNativeQuery("TRUNCATE users").executeUpdate();
            transaction.commit();
            System.out.println("таблицу обнулили");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            if (transaction != null) transaction.rollback();
        }
    }
}
