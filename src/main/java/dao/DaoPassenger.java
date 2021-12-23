package dao;

import model.Bus;
import model.Passenger;
import model.Ticket;
import model.Travel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class DaoPassenger {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(Passenger passenger){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(passenger);
        transaction.commit();
        session.close();
    }

    public List<Passenger> findById(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Passenger> query = session.createQuery("from Passenger p where p.id=:id");
        query.setParameter("id",id);
        List<Passenger> results = query.getResultList();
        transaction.commit();
        session.close();
        return results;
    }

    public void saveTicket(int passengerId, Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Passenger passenger = session.get(Passenger.class,passengerId);
        if (passenger != null) {
            passenger.setTicket(ticket);
            session.update(passenger);
        }
        transaction.commit();
        session.close();
    }

}
