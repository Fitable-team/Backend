package net.fittable.domain.premises;

import lombok.*;
import net.fittable.domain.business.Studio;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LOCATION")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Location {

    @Id
    @GeneratedValue
    private long id;

    private String superDistrict;
    private String lowerDistrict;
    private String name;

    @Embedded
    private Coordinate coordinate;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<Studio> storesIncluded;
}
