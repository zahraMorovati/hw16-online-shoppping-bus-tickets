package service;

import dao.DaoBus;
import model.Bus;
import model.Driver;
import model.enumation.BusStatus;
import model.enumation.BusType;
import view.GetValidData;
import java.util.List;

public class BusService {

    DaoBus daoBus = new DaoBus();

    public void save(Bus bus) {
        daoBus.save(bus);
        System.out.println("bus successfully saved!");
    }

    public Bus findById(int busId) {
        List<Bus> busList = daoBus.findById(busId);
        if (busList.isEmpty())
            throw new RuntimeException("cannot find bus!");
        return busList.get(0);
    }

    public Bus getBusInfo() {

        String ownerName = GetValidData.getValidString("owner family name: ");
        String pelaque = GetValidData.getValidString("pelack: ");
        int chairCount = GetValidData.getValidInt("chair count: ");
        BusType busType = GetValidData.getValidBusType();

        Bus bus = setBusInfo(ownerName, pelaque, chairCount, busType);
        return bus;
    }

    private Bus setBusInfo(String ownerName, String pelaque, int chairCount, BusType busType) {
        Bus bus = Bus.BusBuilder.aBus().setOwnerName(ownerName)
                .setPelaque(pelaque)
                .setChairCount(chairCount)
                .setStatus(BusStatus.OFF_TRAVEL)
                .setBusType(busType).build();
        return bus;
    }

    public List<Bus> getAllBuses(){
        List<Bus> buses = daoBus.findAll();
        if(buses.isEmpty())
            throw new RuntimeException("there is no bus!");
        else return buses;
    }
}
