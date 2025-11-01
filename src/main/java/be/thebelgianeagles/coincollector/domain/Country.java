package be.thebelgianeagles.coincollector.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private COUNTRY_NAME name;
    private String url;

    public Country(COUNTRY_NAME name, String url) {
        this.name = name;
        this.url = url;
    }
}
