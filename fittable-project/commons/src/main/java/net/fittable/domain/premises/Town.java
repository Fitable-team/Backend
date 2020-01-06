package net.fittable.domain.premises;

import net.fittable.domain.business.Store;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TOWN")
public class Town {

    @Id
    @GeneratedValue
    private long id;

    private String province;
    private String city;
    private String name;

    @Embedded
    private Coordinate coordinate;

    @OneToMany(mappedBy = "town", fetch = FetchType.LAZY)
    private List<Store> storesIncluded;
}
