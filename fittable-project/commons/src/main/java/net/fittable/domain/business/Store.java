package net.fittable.domain.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.fittable.domain.premises.Town;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
    private List<Slot> slots = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "STORE_TOWN_ID")
    private Town town;

    @Builder
    public Store(String name, BusinessOwner owner, List<Slot> slots, Town town) {
        this.name = name;
        this.owner = owner;
        this.slots = slots;
        this.town = town;
    }

    public List<Slot> getUnreservedSlots() {
        return this.slots.stream()
                .filter(slot -> !slot.isFullyBooked())
                .collect(Collectors.toList());
    }

}
