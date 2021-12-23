package service;

import dao.DaoTicket;
import dao.DaoTravel;
import dto.DtoReportTravel;
import dto.DtoTravel;
import model.Ticket;
import model.Travel;
import model.enumation.BusType;

import java.util.Date;
import java.util.List;

public class TravelService {
    DaoTravel daoTravel = new DaoTravel();


    public List<DtoReportTravel> getMangerReport() {
        List<DtoReportTravel> reportTravels = daoTravel.managerReport();
        if (reportTravels.isEmpty())
            throw new RuntimeException("there is no report!");
        else return reportTravels;
    }

    public void save(Travel travel) {
        daoTravel.save(travel);
        System.out.println("travel successfully saved!");
    }

    public Travel findById(int travelId) {
        Travel travel = daoTravel.findById(travelId);
        if (travel == null)
            throw new RuntimeException("cannot find travel!");
        return travel;
    }

    public List<DtoTravel> getTravels(String origin, String destination, Date date,
                                      String companyName, BusType busType, double minPrice, double maxPrice,
                                      Date startTime, Date endTime){
        return daoTravel.getTickets(origin,destination,date,companyName,busType,minPrice,maxPrice,startTime,endTime);
    }

    public void addTicket(int travelId, Ticket ticket,int availableChairs){
        daoTravel.addTicket(travelId,ticket,availableChairs);
    }


}
