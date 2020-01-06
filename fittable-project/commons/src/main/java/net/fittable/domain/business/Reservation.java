package net.fittable.domain.business;

import net.fittable.domain.authentication.ClientMember;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "RESERVATION_ID")
    private long id;

    @OneToOne
    @JoinColumn(name = "RESERVATION_CLIENT_ID")
    private ClientMember reservationClient;

    @ManyToOne
    @JoinColumn(name = "RESERVATION_DESTINATION_ID")
    private Store targetStore;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
