package net.fittable.domain.business;

import lombok.Data;

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

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "RESERVATION_ID")
    private Reservation originatedReservation;

    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private Store targetStore;
}
