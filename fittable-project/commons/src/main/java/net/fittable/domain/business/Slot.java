package net.fittable.domain.business;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "SLOT")
@Data
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

    @Builder
    public Slot(Store targetStore, LocalDateTime startTime, LocalDateTime endTime, int capacity) {
        this.targetStore = targetStore;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;

        targetStore.addSlot(this);
    }

    public boolean isFullyBooked() {
        int requestedCapacity = this.reservations.stream()
                .mapToInt(Reservation::getRequestedCapacity)
                .sum();

        return requestedCapacity > this.capacity || (requestedCapacity == this.capacity);
    }
}
