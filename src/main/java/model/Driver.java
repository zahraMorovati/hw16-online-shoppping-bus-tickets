package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.enumation.Gender;

import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Driver extends Person {
    private double salary;


    public static final class DriverBuilder {
        private Driver driver;

        private DriverBuilder() {
            driver = new Driver();
        }

        public static DriverBuilder aDriver() {
            return new DriverBuilder();
        }

        public DriverBuilder setSalary(double salary) {
            driver.setSalary(salary);
            return this;
        }

        public DriverBuilder setId(int id) {
            driver.setId(id);
            return this;
        }

        public DriverBuilder setName(String name) {
            driver.setName(name);
            return this;
        }

        public DriverBuilder setFamily(String family) {
            driver.setFamily(family);
            return this;
        }

        public DriverBuilder setNationalCode(int nationalCode) {
            driver.setNationalCode(nationalCode);
            return this;
        }

        public DriverBuilder setBirthDate(Date birthDate) {
            driver.setBirthDate(birthDate);
            return this;
        }

        public DriverBuilder setGender(Gender gender) {
            driver.setGender(gender);
            return this;
        }

        public DriverBuilder but() {
            return aDriver().setSalary(driver.getSalary()).setId(driver.getId()).setName(driver.getName()).setFamily(driver.getFamily()).setNationalCode(driver.getNationalCode()).setBirthDate(driver.getBirthDate()).setGender(driver.getGender());
        }

        public Driver build() {
            return driver;
        }
    }
}
