package net.fittable.domain.business;

import lombok.Data;
import net.fittable.domain.business.reservation.Session;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "STORE_LESSON")
@Data
public class Lesson {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "LESSON_TITLE")
    private String title;

    @Column(name = "LESSON_DESCRIPTION")
    private String description;

    @Column(name = "LESSON_INSTRUCTOR")
    private String instructorName;

    @ManyToOne
    @JoinColumn(name = "LESSON_OWNER_STUDIOID")
    private Studio studio;

    @OneToMany(mappedBy = "lesson")
    @Cascade(CascadeType.ALL)
    private Set<Session> sessions;

    public boolean isAvailable() {
        return this.getAvailableSlots().size() > 0;
    }

    public Set<Session> getAvailableSlots() {
        return this.sessions.stream()
                .filter(s -> !s.isFullyBooked())
                .collect(Collectors.toSet());
    }
}
