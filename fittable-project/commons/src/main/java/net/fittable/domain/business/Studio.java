package net.fittable.domain.business;

import lombok.Builder;
import lombok.Data;
import net.fittable.domain.authentication.StudioOwnerMember;
import net.fittable.domain.business.reservation.Session;
import net.fittable.domain.premises.Town;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "STUDIO")
@Data
public class Studio {

    @Id
    @GeneratedValue
    @Column(name = "STUDIO_ID")
    private long id;

    @Column(name = "STUDIO_NAME")
    private String name;

    private String newAddress;

    @ManyToOne
    @JoinColumn(name = "STUDIO_OWNER_ID")
    private StudioOwnerMember owner;

    @OneToMany(mappedBy = "targetStudio", fetch = FetchType.LAZY)
    private Set<Session> sessions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "STORE_TOWN_ID")
    private Town town;

    @OneToMany(mappedBy = "targetStore")
    private List<Review> reviews;

    @Builder
    public Studio(String name, StudioOwnerMember owner, Town town) {
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
