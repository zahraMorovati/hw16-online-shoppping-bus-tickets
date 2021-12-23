package dto;

import lombok.Data;

import java.util.Date;

@Data
public class DtoReportTravel {
    private int idTravel;
    private int chairCount;
    private Date movingDate;


    @Override
    public String toString() {
        return "DtoReportTravel{" +
                "idTravel=" + idTravel +
                ", avaiable chairs =" + chairCount +
                ", movingDate=" + movingDate +
                '}';
    }
}
