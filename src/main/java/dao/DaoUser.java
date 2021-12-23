package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class DaoUser {

    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public boolean isUsernameDupplicated(String username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<User> userQuery = session.createQuery("from User u where u.userName=:username");
        userQuery.setParameter("username", username);
        List<User> userList = userQuery.getResultList();

        transaction.commit();
        session.close();

        if (userList.isEmpty()) {
            return false;
        } else return true;
    }

    public List<User> findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.id=:id");
        query.setParameter("id", id);
        List<User> results = query.getResultList();
        transaction.commit();
        session.close();
        return results;
    }

    public List<User> findByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.userName=:username and u.password=:password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> results = query.getResultList();
        transaction.commit();
        session.close();
        return results;
    }

    public void increaseBalance(double amount, int userId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, userId);
        if (user != null) {
            double newAmount = amount + user.getBalance();
            user.setBalance(newAmount);
            session.update(user);
            transaction.commit();
            session.close();
        }else throw new RuntimeException("operation faild!");


    }
}
