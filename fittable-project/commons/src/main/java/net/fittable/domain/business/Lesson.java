package net.fittable.domain.business;

import lombok.Data;

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

    @OneToMany
    private Set<Slot> slots;

    public boolean isAvailable() {
        return this.getAvailableSlots().size() > 0;
    }

    public Set<Slot> getAvailableSlots() {
        return this.slots.stream()
                .filter(s -> !s.isFullyBooked())
                .collect(Collectors.toSet());
    }
}
