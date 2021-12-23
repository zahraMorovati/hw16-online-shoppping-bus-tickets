package model;

import lombok.Data;
import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String origin;
    private String destination;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date movingDate;
    private double price;
    private String companyName;
    @OneToOne
    private Driver driver;
    @OneToOne
    private Bus bus;
    private int availableChairs;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Ticket> ticketList = new ArrayList<>();


    public static final class TravelBuilder {
        private Travel travel;

        private TravelBuilder() {
            travel = new Travel();
        }

        public static TravelBuilder aTravel() {
            return new TravelBuilder();
        }

        public TravelBuilder setId(int id) {
            travel.setId(id);
            return this;
        }

        public TravelBuilder setOrigin(String origin) {
            travel.setOrigin(origin);
            return this;
        }

        public TravelBuilder setDestination(String destination) {
            travel.setDestination(destination);
            return this;
        }

        public TravelBuilder setMovingDate(Date movingDate) {
            travel.setMovingDate(movingDate);
            return this;
        }

        public TravelBuilder setPrice(double price) {
            travel.setPrice(price);
            return this;
        }

        public TravelBuilder setCompanyName(String companyName) {
            travel.setCompanyName(companyName);
            return this;
        }

        public TravelBuilder setDriver(Driver driver) {
            travel.setDriver(driver);
            return this;
        }

        public TravelBuilder setBus(Bus bus) {
            travel.setBus(bus);
            return this;
        }

        public TravelBuilder setAvailableChairs(int availableChairs) {
            travel.setAvailableChairs(availableChairs);
            return this;
        }

        public TravelBuilder setTicketList(List<Ticket> ticketList) {
            travel.setTicketList(ticketList);
            return this;
        }

        public Travel build() {
            return travel;
        }
    }
}
