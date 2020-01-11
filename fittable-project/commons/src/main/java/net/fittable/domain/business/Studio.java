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

    @Column(name = "STUDIO_DETAILED_ADDRESS")
    private String detailedAddress;

    @ManyToOne
    @JoinColumn(name = "STUDIO_OWNER_ID")
    private StudioOwnerMember owner;

    @OneToMany(mappedBy = "targetStudio", fetch = FetchType.LAZY)
    private Set<Session> sessions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "STORE_TOWN_ID")
    private Town town;

    @OneToMany(mappedBy = "targetStudio")
    private List<Review> reviews;

    @Column(name = "STUDIO_IMAGE_LIST")
    private StudioImageList imageList;

    @Column(name = "STUDIO_INTRO_MOVIE")
    private String introductionMovie;

    @Column(name = "STUDIO_DIRECTIONS")
    private String directions;

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
