package net.fittable.domain.premises;

import lombok.*;
import net.fittable.domain.business.Studio;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TOWN")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Town {

    @Id
    @GeneratedValue
    private long id;

    private String superDistrict;
    private String lowerDistrict;
    private String name;

    @Embedded
    private Coordinate coordinate;

    @OneToMany(mappedBy = "town", fetch = FetchType.LAZY)
    private List<Studio> storesIncluded;
}
