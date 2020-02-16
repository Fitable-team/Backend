package net.fittable.domain.premises;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@Builder
public class Coordinate {

    private double latitude;
    private double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double calculateDistanceInKilometer(Coordinate coordinate) {
        if(this.equals(coordinate)) {
            return 0.0F;
        }

        double theta = this.longitude - coordinate.longitude;
        double dist = Math.sin(
                Math.toRadians(this.latitude)) * Math.sin(Math.toRadians(coordinate.latitude)) +
                Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(coordinate.latitude))
                        * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;

        return dist * 1.609344;
    }
}
