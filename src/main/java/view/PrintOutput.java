package view;

import dto.DtoReportTravel;
import dto.DtoTravel;
import model.Bus;
import model.Driver;
import model.Travel;
import service.BusService;
import service.DriverService;

import java.util.Date;
import java.util.List;

public class PrintOutput {
    public static void printMangerReport(List<DtoReportTravel> reportTravels) {
        reportTravels.stream().map(i -> i.toString()).forEach(System.out::println);
    }

    public static void printTravelList(List<DtoTravel> travelList) {
        travelList.stream().map(i -> i.toString()).forEach(System.out::println);
    }

    public static void printDriversList(List<Driver> driverList) {
        driverList.stream().map(i -> i.getId() + ") " + i.getName() + " " + i.getFamily()).forEach(System.out::println);
    }

    public static void printBusList(List<Bus> busList) {
        busList.stream().map(i -> i.getId() + ") " + i.getOwnerName() + " " + i.getStatus()).forEach(System.out::println);
    }

    public static Travel getTravelInfo(BusService busService, DriverService driverService) {

        String origin = GetValidData.getValidName("origin: ");
        String destination = GetValidData.getValidName("destination: ");
        Date movingDate = GetValidData.getValidDate("moving date: ");
        double price = GetValidData.getValidDouble("price: ");
        String companyName = GetValidData.getValidName("company name: ");
        Driver driver = getValidDriver(driverService);
        Bus bus = getValidBus(busService);
        int availableChairs = bus.getChairCount();
        Travel travel = setTravel(origin, destination, movingDate, price, companyName, driver, bus, availableChairs);
        return travel;
    }

    private static Travel setTravel(String origin, String destination, Date movingDate, double price, String companyName, Driver driver, Bus bus, int availableChairs) {
        Travel travel = Travel.TravelBuilder.aTravel()
                .setOrigin(origin).setDestination(destination)
                .setMovingDate(movingDate).setPrice(price)
                .setCompanyName(companyName).setDriver(driver)
                .setBus(bus).setAvailableChairs(availableChairs).build();
        return travel;
    }

    private static Bus getValidBus(BusService busService) {
        Bus bus = null;
        try {
            printBusList(busService.getAllBuses());
            int busId = GetValidData.getValidInt("enter bus id: ");
            bus = busService.findById(busId);
        } catch (RuntimeException e) {
            System.out.println("invalid bus id!");
            return getValidBus(busService);
        }
        return bus;
    }

    private static Driver getValidDriver(DriverService driverService) {
        Driver driver=null;
        try {
            printDriversList(driverService.getAllDrivers());
            int driverId = GetValidData.getValidInt("enter driver id: ");
            driver = driverService.findById(driverId);
        } catch (RuntimeException e) {
            System.out.println("invalid driver id!");
            return getValidDriver(driverService);
        }
        return driver;
    }
}
