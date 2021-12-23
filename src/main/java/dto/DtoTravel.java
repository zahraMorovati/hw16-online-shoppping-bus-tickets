package dto;

import lombok.Data;
import model.enumation.BusType;

import java.util.Date;

@Data
public class DtoTravel {
    private int idTravel;
    private String companyName;
    private BusType busType;
    private Date movingDate;
    private double price;
    private int chairCount;

    @Override
    public String toString() {
        return
                "{ idTravel= " + idTravel +
                " companyName= " + companyName + '\'' +
                " busType= " + busType +
                " movingDate= " + movingDate +
                " price= " + price +
                " chairCount= " + chairCount+"}\n";
    }
}
