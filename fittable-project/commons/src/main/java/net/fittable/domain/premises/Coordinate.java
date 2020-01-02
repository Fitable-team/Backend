package net.fittable.domain.premises;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Data
@Builder
public class Coordinate {

    private final double latitude;
    private final double longitude;

    public Coordinate(float latitude, float longitude) {
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
