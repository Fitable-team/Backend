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

    @OneToOne
    @JoinColumn(name = "CLIENT_ID")
    private ClientMember reservedClient;

    @ManyToOne
    @JoinColumn(name = "TARGET_SLOT_ID")
    private Slot targetSlot;

    private int requestedCapacity = 1;

    @Builder
    public Reservation(ClientMember reservedClient, Slot targetSlot, int requestedCapacity) {
        this.reservedClient = reservedClient;
        this.targetSlot = targetSlot;
        this.requestedCapacity = requestedCapacity;
    }
}
