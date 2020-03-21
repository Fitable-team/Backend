package net.fittable.domain.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.fittable.domain.business.reservation.Session;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "STORE_LESSON")
@Getter
@Setter
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LESSON_OWNER_STUDIOID")
    @JsonIgnore
    private Studio studio;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private Set<Session> sessions;

    public boolean isAvailable() {
        return this.getAvailableSlots().size() > 0;
    }

    public Set<Session> getAvailableSlots() {
        return this.sessions.stream()
                .filter(s -> !s.isFullyBooked())
                .collect(Collectors.toSet());
    }

    public List<Session> getAvailableSessions() {
        return this.sessions.stream()
                .filter(Session::isOngoingOrSoon)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id == lesson.id &&
                Objects.equals(title, lesson.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
