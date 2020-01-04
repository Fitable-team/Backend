package net.fittable.domain.business;

import lombok.Builder;
import lombok.Data;
import net.fittable.domain.authentication.ClientMember;

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
    private Slot targetSlot;

    @OneToOne(mappedBy = "originatedReservation")
    private Review userReview;

    private int requestedCapacity = 1;
    private boolean used;

    private boolean wasAbsent = false;

    @Builder
    public Reservation(ClientMember reservedClient, Slot targetSlot, int requestedCapacity) {
        this.reservedClient = reservedClient;
        this.targetSlot = targetSlot;
        this.requestedCapacity = requestedCapacity;
    }

    public void markAsAbsent() {
        this.wasAbsent = true;
    }

    public boolean userHasWroteReview() {
        return this.userReview != null;
    }

    public boolean isEligibleForReviewWriting() {
        return this.userReview == null && this.wasAbsent == false;
    }
}
