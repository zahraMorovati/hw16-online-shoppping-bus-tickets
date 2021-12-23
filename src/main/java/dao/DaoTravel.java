package dao;

import com.mysql.cj.util.StringUtils;
import dto.DtoReportTravel;
import dto.DtoTravel;
import model.Ticket;
import model.Travel;
import model.enumation.BusType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import util.HibernateUtil;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class DaoTravel {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(Travel travel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(travel);
        transaction.commit();
        session.close();
    }

    public Travel findById(int travelId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Travel travel = getTravelById(travelId, session);
        transaction.commit();
        session.close();
        return travel;
    }

    private Travel getTravelById(int travelId, Session session) {
        Query<Travel> query = session.createQuery("from Travel t where t.id=:id");
        query.setParameter("id", travelId);
        List<Travel> results = query.getResultList();
        return results.get(0);
    }

    public void addTicket(int travelId, Ticket ticket,int availableChairs) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Travel travel = getTravelById(travelId, session);
        if (travel != null) {
            travel.getTicketList().add(ticket);
            travel.setAvailableChairs(availableChairs);
            session.update(travel);
        }
        transaction.commit();
        session.close();
    }

    public List<DtoTravel> getTickets(String origin, String destination, Date date,
                                  String companyName, BusType busType, double minPrice, double maxPrice,
                                  Date startTime, Date endTime) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Travel.class, "t");
        criteria.createAlias("t.bus", "b");

        if (!StringUtils.isNullOrEmpty(origin)) {
            criteria.add(Restrictions.eq("t.origin", origin));
        } else throw new RuntimeException("origin is not valid!");

        if (!StringUtils.isNullOrEmpty(destination)) {
            criteria.add(Restrictions.eq("t.destination", destination));
        } else throw new RuntimeException("destination is not valid!");

        if (date != null) {
            criteria.add(Restrictions.eq("t.movingDate", date));
        }

        if (!StringUtils.isNullOrEmpty(companyName)) {
            criteria.add(Restrictions.eq("t.companyName", companyName));
        }

        if (busType != null) {
            criteria.add(Restrictions.eq("b.busType", busType));
        }

        if (minPrice > 0 && maxPrice > 0) {
            criteria.add(Restrictions.between("t.price", minPrice, maxPrice));
        }

        if (startTime != null && endTime != null) {
            criteria.add(Restrictions.between("t.movingDate", startTime, endTime));
        }

        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("t.id"), "idTravel")
                .add(Projections.property("t.companyName"), "companyName")
                .add(Projections.property("b.busType"), "busType")
                .add(Projections.property("t.movingDate"), "movingDate")
                .add(Projections.property("t.price"), "price")
                .add(Projections.property("t.availableChairs"), "chairCount")
        );

        criteria.setResultTransformer(Transformers.aliasToBean(DtoTravel.class));
        criteria.setMaxResults(10);
        List<DtoTravel> list = criteria.list();
        transaction.commit();
        session.close();
        return list;
    }

    public List<DtoReportTravel> managerReport() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Travel.class, "t");
        criteria.createAlias("t.bus", "b");

        criteria.addOrder(Order.desc("t.movingDate"));

        criteria.setProjection(Projections.projectionList()
                .add(Projections.groupProperty("b.busType"))
                .add(Projections.property("t.id"), "idTravel")
                .add(Projections.property("t.availableChairs"), "chairCount")
                .add(Projections.property("t.movingDate"), "movingDate"));

        criteria.setResultTransformer(Transformers.aliasToBean(DtoReportTravel.class));
        List<DtoReportTravel> list = criteria.list();
        transaction.commit();
        session.close();
        return list;
    }
}
