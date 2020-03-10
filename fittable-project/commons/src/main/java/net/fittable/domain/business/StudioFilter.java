package net.fittable.domain.business;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import net.fittable.domain.business.enums.Amenity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STUDIO_FILTER")
@Data
@NoArgsConstructor
public class StudioFilter {

    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    private Amenity attribute;

    @ManyToMany
    private List<Studio> studiosWithAttribute;

    public static StudioFilter generateEntity(String attribute) {
        Amenity amenity = Amenity.fromDataString(attribute);

        StudioFilter studioFilter = new StudioFilter();
        studioFilter.setAttribute(amenity);

        return studioFilter;
    }
}
