package entities;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@NoArgsConstructor
public class Travel {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    private Vehicle vehicle;

    @OneToOne
    private Route route;

    private int numberOfTimes;
    private Duration travelTime;

    // Costruttore, getter e setter

    public Travel(Vehicle vehicle, Route route) {
        this.vehicle = vehicle;
        this.route = route;
        this.numberOfTimes = 0;
        this.travelTime = Duration.ZERO;
    }

    public void incrementNumberOfTimes() {
        numberOfTimes++;
    }

    public void addTravelTime(Duration duration) {
        travelTime = travelTime.plus(duration);
    }

    // Altri metodi utili, come calcolare la durata media di percorrenza della tratta, ecc.
}