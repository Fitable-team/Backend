package net.fittable.domain.business;

import net.fittable.domain.authentication.ClientMember;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "SLOT")
public class Slot {

    @Id
    @GeneratedValue
    @Column(name = "RESERVATION_ID")
    private long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATION_CLIENT_ID")
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "RESERVATION_DESTINATION_ID")
    private Store targetStore;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private int capacity = 1;

    public boolean isFullyBooked() {
        int requestedCapacity = this.reservations.stream()
                .mapToInt(Reservation::getRequestedCapacity)
                .sum();

        return requestedCapacity > this.capacity || (requestedCapacity == this.capacity);
    }
}
