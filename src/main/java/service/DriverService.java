package service;

import dao.DaoDriver;
import model.Driver;
import model.enumation.Gender;
import view.GetValidData;

import java.util.Date;
import java.util.List;

public class DriverService {
    DaoDriver daoDriver = new DaoDriver();

    public void save(Driver driver) {
        daoDriver.save(driver);
        System.out.println("driver successfully saved!");
    }

    public Driver findById(int driverId) {
        List<Driver> driverList = daoDriver.findById(driverId);
        if (driverList.isEmpty())
            throw new RuntimeException("cannot find driver!");
        return driverList.get(0);
    }

    public Driver getDriverInfo() {
        String name = GetValidData.getValidName("name: ");
        String family = GetValidData.getValidName("family: ");
        int nationalCode = GetValidData.getValidInt("national code: ");
        Date birthDate = GetValidData.getValidDate("birthdate: ");
        Gender gender = GetValidData.getValidGender();
        double salary = GetValidData.getValidDouble("salary: ");
        return setDriver(name, family, nationalCode, birthDate, gender, salary);
    }

    private Driver setDriver(String name, String family, int nationalCode, Date birthDate, Gender gender, double salary) {
        Driver driver= Driver.DriverBuilder.aDriver()
                .setName(name)
                .setFamily(family)
                .setNationalCode(nationalCode)
                .setBirthDate(birthDate)
                .setGender(gender)
                .setSalary(salary).build();
        return driver;
    }

    public List<Driver> getAllDrivers(){
        List<Driver> drivers = daoDriver.findAll();
        if(drivers.isEmpty())
            throw new RuntimeException("there is no driver!");
        else return drivers;
    }
}
