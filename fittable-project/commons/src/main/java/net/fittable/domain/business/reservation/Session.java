package net.fittable.domain.business.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.fittable.domain.business.Lesson;
import net.fittable.domain.business.Studio;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "SESSION")
@Setter
@Getter
@NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue
    @Column(name = "RESERVATION_ID")
    private long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATION_CLIENT_ID")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "RESERVATION_DESTINATION_ID")
    private Studio targetStudio;

    @Column(name = "SESSION_ROOM")
    private String room;

    @Column(name = "SESSION_PRICE")
    private Integer price;

    @Column(name = "SESSION_LEVEL")
    @Enumerated(EnumType.STRING)
    private ClassLevel classLevel;

    @Column(name = "SESSION_STARTTIME")
    private LocalTime startTime;

    @Column(name = "SESSION_ENDTIME")
    private LocalTime endTime;

    @Column(name = "SESSION_REGULAR_DAY")
    @Embedded
    private RegularSession regularSession;

    @ManyToOne
    @JoinColumn(name = "SESSION_LESSON_ID")
    @JsonIgnore
    private Lesson lesson;

    private int capacity = 1;

    @Builder
    public Session(List<Reservation> reservations, Studio targetStudio, String room, Integer price, ClassLevel classLevel, LocalTime startTime, LocalTime endTime, RegularSession regularSession, int capacity) {
        this.reservations = reservations;
        this.targetStudio = targetStudio;
        this.room = room;
        this.price = price;
        this.classLevel = classLevel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.regularSession = regularSession;
        this.capacity = capacity;
    }

    public boolean isFullyBooked() {
        int requestedCapacity = this.reservations.stream()
                .mapToInt(Reservation::getRequestedCapacity)
                .sum();

        return requestedCapacity > this.capacity || (requestedCapacity == this.capacity);
    }

    public boolean isOngoingOrSoon() {
        LocalTime until = LocalTime.now().plus(6, ChronoUnit.HOURS);

        if(this.regularSession != null && this.regularSession.hasRegularSessions()) {
            if(this.regularSession.isClassScheduledToday()) {
                return this.startTime.isBefore(until);
            }
            return false;
        }

        return this.startTime.isBefore(until);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id == session.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
