package service;

import dao.DaoPassenger;
import model.Passenger;
import model.Ticket;
import model.enumation.Gender;
import view.GetValidData;

import java.util.Date;
import java.util.List;

public class PassengerService {

    DaoPassenger daoPassenger = new DaoPassenger();

    public void save(Passenger passenger) {
        daoPassenger.save(passenger);
        System.out.println("passenger successfully saved!");
    }

    public Passenger findById(int passengerId) {
        List<Passenger> passengerList = daoPassenger.findById(passengerId);
        if (passengerList.isEmpty())
            throw new RuntimeException("cannot find passenger!");
        return passengerList.get(0);
    }

    public Passenger getPassengerInfo() {
        String name = GetValidData.getValidName("name: ");
        String family = GetValidData.getValidName("family: ");
        int nationalCode = GetValidData.getValidInt("national code: ");
        Date birthDate = GetValidData.getValidDate("birthdate: ");
        Gender gender = GetValidData.getValidGender();

        Passenger passenger = setPassengerInfo(name, family, nationalCode, birthDate, gender);
        return passenger;
    }

    private Passenger setPassengerInfo(String name, String family, int nationalCode, Date birthDate, Gender gender) {
        Passenger passenger = Passenger.PassengerBuilder.aPassenger()
                .setName(name)
                .setFamily(family)
                .setNationalCode(nationalCode)
                .setBirthDate(birthDate)
                .setGender(gender).build();
        return passenger;
    }

    public void saveTicket(int id, Ticket ticket){
        daoPassenger.saveTicket(id,ticket);
    }

}
