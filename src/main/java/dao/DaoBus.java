package dao;

import model.Bus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class DaoBus {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(Bus bus){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(bus);
        transaction.commit();
        session.close();
    }

    public List<Bus> findById(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Bus> query = session.createQuery("from Bus b where b.id=:id");
        query.setParameter("id",id);
        List<Bus> results = query.getResultList();
        transaction.commit();
        session.close();
        return results;
    }

    public List<Bus> findAll(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Bus> query = session.createQuery("from Bus");
        List<Bus> results = query.getResultList();
        transaction.commit();
        session.close();
        return results;
    }
}
