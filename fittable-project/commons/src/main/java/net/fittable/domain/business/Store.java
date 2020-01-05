package net.fittable.domain.business;

import lombok.Builder;
import lombok.Data;
import net.fittable.domain.business.reservation.Session;
import net.fittable.domain.premises.Town;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "BUSINESS_STORE")
@Data
public class Store {

    @Id
    @GeneratedValue
    @Column(name = "STORE_ID")
    private long id;

    @Column(name = "STORE_NAME")
    private String name;

    private String newAddress;

    @OneToOne
    @JoinColumn(name = "STORE_OWNER_ID")
    private BusinessOwner owner;

    @OneToMany(mappedBy = "targetStore", fetch = FetchType.LAZY)
    private Set<Session> sessions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "STORE_TOWN_ID")
    private Town town;

    @OneToMany(mappedBy = "targetStore")
    private List<Review> reviews;

    @Builder
    public Store(String name, BusinessOwner owner, Town town) {
        this.name = name;
        this.owner = owner;
        this.town = town;
    }

    public void addSlot(Session session) {
        if(this.sessions.contains(session)) {
            return;
        }

        this.sessions.add(session);
    }

    public List<Session> getUnreservedSlots() {
        return this.sessions.stream()
                .filter(session -> !session.isFullyBooked())
                .collect(Collectors.toList());
    }
}
