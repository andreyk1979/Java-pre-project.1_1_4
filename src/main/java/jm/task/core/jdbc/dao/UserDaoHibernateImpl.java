package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS userdb.User(id MEDIUMINT not NULL AUTO_INCREMENT, name VARCHAR(50) not NULL," +
                    "lastname VARCHAR (50) not NULL,age int not NULL,PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана");
        } catch (Exception e) {
            System.out.println();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS userdb.User").executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            System.out.println();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        }
    }

    @Override
    public void removeUserById(long id) {
        User user;
        Transaction transaction;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
            System.out.println("User с ID – " + id + " удален из базы данных");
        } catch (Exception e) {
            System.out.println();
        }
    }

    @Override
    public List<User> getAllUsers() {

        Transaction transaction;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("from User").list();
            transaction.commit();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
            System.out.println("Таблица очищена");
        }
    }
}

