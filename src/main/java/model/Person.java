package model;

import lombok.Data;
import model.enumation.Gender;
import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String family;
    @Column(name = "national_Code")
    private int nationalCode;
    @Column(name = "birth_Date")
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
