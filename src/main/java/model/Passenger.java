package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.enumation.Gender;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Passenger extends Person {
    @OneToOne
    private Ticket ticket;


    public static final class PassengerBuilder {
        private Passenger passenger;

        private PassengerBuilder() {
            passenger = new Passenger();
        }

        public static PassengerBuilder aPassenger() {
            return new PassengerBuilder();
        }

        public PassengerBuilder setTicket(Ticket ticket) {
            passenger.setTicket(ticket);
            return this;
        }

        public PassengerBuilder setId(int id) {
            passenger.setId(id);
            return this;
        }

        public PassengerBuilder setName(String name) {
            passenger.setName(name);
            return this;
        }

        public PassengerBuilder setFamily(String family) {
            passenger.setFamily(family);
            return this;
        }

        public PassengerBuilder setNationalCode(int nationalCode) {
            passenger.setNationalCode(nationalCode);
            return this;
        }

        public PassengerBuilder setBirthDate(Date birthDate) {
            passenger.setBirthDate(birthDate);
            return this;
        }

        public PassengerBuilder setGender(Gender gender) {
            passenger.setGender(gender);
            return this;
        }

        public Passenger build() {
            return passenger;
        }
    }
}
