package net.fittable.domain.business;

import net.fittable.domain.authentication.ClientMember;

import javax.persistence.*;

@Entity
@Table(name = "RESERVATION")
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

    public ClientMember getReservedClient() {
        return reservedClient;
    }

    public void setReservedClient(ClientMember reservedClient) {
        this.reservedClient = reservedClient;
    }

    public Slot getTargetSlot() {
        return targetSlot;
    }

    public void setTargetSlot(Slot targetSlot) {
        this.targetSlot = targetSlot;
    }

    public int getRequestedCapacity() {
        return requestedCapacity;
    }

    public void setRequestedCapacity(int requestedCapacity) {
        this.requestedCapacity = requestedCapacity;
    }
}
