package dao;

import model.Bus;
import model.Driver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class DaoDriver {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(Driver driver){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(driver);
        transaction.commit();
        session.close();
    }

    public List<Driver> findById(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Driver> query = session.createQuery("from Driver d where d.id=:id");
        query.setParameter("id",id);
        List<Driver> results = query.getResultList();
        transaction.commit();
        session.close();
        return results;
    }

    public List<Driver> findAll(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Driver> query = session.createQuery("from Driver");
        List<Driver> results = query.getResultList();
        transaction.commit();
        session.close();
        return results;
    }

}
