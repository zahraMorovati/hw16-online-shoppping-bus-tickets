package service;

import dao.DaoTicket;
import model.Ticket;
import model.User;

public class TicketService {
    DaoTicket daoTicket = new DaoTicket();
    public void save(Ticket ticket){
        daoTicket.save(ticket);
    }

}
