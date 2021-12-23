package dao;

import model.Bus;
import model.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class DaoTicket {

    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(Ticket ticket){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
    }
}
