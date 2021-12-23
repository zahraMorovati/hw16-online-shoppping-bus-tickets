package model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date parchasingDate;
    @OneToOne
    private Passenger passenger;
    @OneToOne
    private Travel travel;


    public static final class TicketBuilder {
        private Ticket ticket;

        private TicketBuilder() {
            ticket = new Ticket();
        }

        public static TicketBuilder aTicket() {
            return new TicketBuilder();
        }

        public TicketBuilder setId(int id) {
            ticket.setId(id);
            return this;
        }

        public TicketBuilder setParchasingDate(Date parchasingDate) {
            ticket.setParchasingDate(parchasingDate);
            return this;
        }

        public TicketBuilder setPassenger(Passenger passenger) {
            ticket.setPassenger(passenger);
            return this;
        }

        public TicketBuilder setTravel(Travel travel) {
            ticket.setTravel(travel);
            return this;
        }

        public Ticket build() {
            return ticket;
        }
    }
}
