package net.fittable.domain.business;

import lombok.Data;
import net.fittable.domain.authentication.ClientMember;

import javax.persistence.*;

@Entity
@Table(name = "STORE_REVIEW")
@Data
public class Review {

    @Id
    @GeneratedValue
    private long id;

    private String content;
    private double starPoint;

    @OneToOne
    @JoinColumn(name = "RESERVATION_ID")
    private Reservation originatedReservation;

    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private Store targetStore;
}
