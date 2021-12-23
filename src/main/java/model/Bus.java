package model;

import lombok.Data;
import model.enumation.BusStatus;
import model.enumation.BusType;

import javax.persistence.*;

@Data
@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "owner_name")
    private String ownerName;
    private String pelaque;
    private int chairCount;
    @Enumerated(EnumType.STRING)
    private BusStatus status;
    @Enumerated(EnumType.STRING)
    private BusType busType;


    public static final class BusBuilder {
        private Bus bus;

        private BusBuilder() {
            bus = new Bus();
        }

        public static BusBuilder aBus() {
            return new BusBuilder();
        }

        public BusBuilder setId(int id) {
            bus.setId(id);
            return this;
        }

        public BusBuilder setOwnerName(String ownerName) {
            bus.setOwnerName(ownerName);
            return this;
        }

        public BusBuilder setPelaque(String pelaque) {
            bus.setPelaque(pelaque);
            return this;
        }

        public BusBuilder setChairCount(int chairCount) {
            bus.setChairCount(chairCount);
            return this;
        }

        public BusBuilder setStatus(BusStatus status) {
            bus.setStatus(status);
            return this;
        }

        public BusBuilder setBusType(BusType busType) {
            bus.setBusType(busType);
            return this;
        }

        public Bus build() {
            return bus;
        }
    }
}
