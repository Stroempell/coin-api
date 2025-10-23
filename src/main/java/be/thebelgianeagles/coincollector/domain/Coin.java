package be.thebelgianeagles.coincollector.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name="coins")
@Getter
@Setter
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private COUNTRY country;
    private int year;
    private String url;

    public Coin(String name, COUNTRY country, int year) {
        this.name = name;
        this.country = country;
        this.year = year;
        this.url = url;
    }

}
