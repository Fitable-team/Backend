package net.fittable.domain.business;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BUSINESS_STORE")
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
    private List<Reservation> reservations = new ArrayList<>();

}
