package net.fittable.domain.business;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.fittable.domain.authentication.StudioOwnerMember;
import net.fittable.domain.business.reservation.Session;
import net.fittable.domain.premises.Coordinate;
import net.fittable.domain.premises.Town;
import net.fittable.persistence.converters.SocialAddressConverter;
import net.fittable.persistence.converters.StudioImageListConverter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "STUDIO")
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Studio {

    @Id
    @GeneratedValue
    @Column(name = "STUDIO_ID")
    private long id;

    @Column(name = "STUDIO_NAME")
    @NotEmpty
    @Size(min = 1, max = 100, message = "이름은 1~100자 사이로 입력해 주세요.")
    private String name;

    @Column(name = "STUDIO_DETAILED_ADDRESS")
    @Size(min = 1, max = 500)
    private String detailedAddress;

    @Embedded
    private Coordinate coordinate;

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

    @Column(name = "STUDIO_SOCIAL_CONTACT")
    @Convert(converter = SocialAddressConverter.class)
    private SocialAddress socialAddress;

    @Column(name = "STUDIO_IMAGE_LIST")
    @Convert(converter = StudioImageListConverter.class)
    private StudioImageList imageList;

    @Column(name = "STUDIO_INTRO_MOVIE")
    private String introductionMovie;

    @Column(name = "STUDIO_DIRECTIONS")
    private String directions;

    @Column(name = "STUDIO_INTRODUCTION")
    private String studioIntroduction;

    @Column(name = "STUDIO_NOTICE")
    private String notice;

    public Studio(String name, StudioOwnerMember owner, Town town) {
        this.name = name;
        this.owner = owner;
        this.town = town;
    }

    public void addSession(Session session) {
        if(this.sessions.contains(session)) {
            return;
        }

        this.sessions.add(session);
    }

    public List<Session> getUnreservedSessions() {
        return this.sessions.stream()
                .filter(session -> !session.isFullyBooked())
                .collect(Collectors.toList());
    }
}
