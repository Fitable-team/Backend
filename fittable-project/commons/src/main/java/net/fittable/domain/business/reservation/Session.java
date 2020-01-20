package net.fittable.domain.business.reservation;

import lombok.Builder;
import lombok.Data;
import net.fittable.domain.business.Studio;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

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
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "RESERVATION_DESTINATION_ID")
    private Studio targetStudio;

    @Column(name = "SESSION_INSTRUCTOR")
    private String instructorName;

    @Column(name = "SESSION_ROOM")
    private String room;

    @Column(name = "SESSION_PRICE")
    private Integer price;

    @Column(name = "SESSION_STARTTIME")
    private LocalDateTime startTime;

    @Column(name = "SESSION_ENDTIME")
    private LocalDateTime endTime;

    @Column(name = "SESSION_REGULAR_DAY")
    private RegularSession regularSession;

    private int capacity = 1;

    @Builder
    public Session(Studio targetStudio, LocalDateTime startTime, LocalDateTime endTime, int capacity, String instructorName) {
        this.targetStudio = targetStudio;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.instructorName = instructorName;

        targetStudio.addSession(this);
    }

    public boolean isFullyBooked() {
        int requestedCapacity = this.reservations.stream()
                .mapToInt(Reservation::getRequestedCapacity)
                .sum();

        return requestedCapacity > this.capacity || (requestedCapacity == this.capacity);
    }

    public void setAllReservationAsUsed() {
        if(LocalDateTime.now().toEpochSecond(ZoneOffset.of("Asia/Seoul")) < this.endTime.toEpochSecond(ZoneOffset.of("Asia/Seoul"))) {
            return;
        }

        this.reservations = this.reservations.stream()
                .peek(r -> r.setUsed(true))
                .collect(Collectors.toList());
    }
}
