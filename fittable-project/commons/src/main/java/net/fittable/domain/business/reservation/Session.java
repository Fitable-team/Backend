package net.fittable.domain.business.reservation;

import lombok.Builder;
import lombok.Data;
import net.fittable.domain.business.Studio;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "SESSION")
@Data
public class Session {

    @Id
    @GeneratedValue
    @Column(name = "RESERVATION_ID")
    private long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATION_CLIENT_ID")
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "RESERVATION_DESTINATION_ID")
    private Studio targetStudio;

    @Column(name = "SESSION_STARTTIME")
    private LocalDateTime startTime;

    @Column(name = "SESSION_ENDTIME")
    private LocalDateTime endTime;

    @Column(name = "SLOT_REGULAR_DAY")
    private String yoil;

    private int capacity = 1;

    @Builder
    public Session(Studio targetStudio, LocalDateTime startTime, LocalDateTime endTime, int capacity) {
        this.targetStudio = targetStudio;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;

        targetStudio.addSlot(this);
    }

    public boolean isFullyBooked() {
        int requestedCapacity = this.reservations.stream()
                .mapToInt(Reservation::getRequestedCapacity)
                .sum();

        return requestedCapacity > this.capacity || (requestedCapacity == this.capacity);
    }
}
