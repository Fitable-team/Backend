package net.fittable.domain.business.reservation;

import lombok.Builder;
import lombok.Data;
import net.fittable.domain.authentication.ClientMember;
import net.fittable.domain.business.Review;

import javax.persistence.*;

@Entity
@Table(name = "RESERVATION")
@Data
public class Reservation {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private ClientMember reservedClient;

    @ManyToOne
    @JoinColumn(name = "TARGET_SLOT_ID")
    private Session targetSession;

    @OneToOne(mappedBy = "originatedReservation", cascade = CascadeType.PERSIST)
    private Review userReview;

    private int requestedCapacity = 1;
    private boolean used = false;
    private boolean accepted = false;
    private boolean wasAbsent = false;

    @Builder
    public Reservation(ClientMember reservedClient, Session targetSession, int requestedCapacity) {
        this.reservedClient = reservedClient;
        this.targetSession = targetSession;
        this.requestedCapacity = requestedCapacity;
    }

    public void markAsAccepted() {
        this.accepted = true;
    }

    public void markAsAbsent() {
        this.wasAbsent = true;
    }

    public boolean userHasWroteReview() {
        return this.userReview != null;
    }

    public boolean isEligibleForWritingReview() {
        return this.userReview == null && this.wasAbsent == false;
    }
}
