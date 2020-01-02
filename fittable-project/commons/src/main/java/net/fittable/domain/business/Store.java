package net.fittable.domain.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.fittable.domain.premises.Town;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToOne
    @JoinColumn(name = "STORE_OWNER_ID")
    private BusinessOwner owner;

    @OneToMany(mappedBy = "targetStore", fetch = FetchType.LAZY)
    private Set<Slot> slots = new HashSet<>();

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

    public void addSlot(Slot slot) {
        if(this.slots.contains(slot)) {
            return;
        }

        this.slots.add(slot);
    }

    public List<Slot> getUnreservedSlots() {
        return this.slots.stream()
                .filter(slot -> !slot.isFullyBooked())
                .collect(Collectors.toList());
    }
}
