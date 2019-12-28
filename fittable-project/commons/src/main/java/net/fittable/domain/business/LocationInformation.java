package net.fittable.domain.business;

import javax.persistence.Embeddable;

@Embeddable
public class LocationInformation {

    private String province;
    private String city;
    private String district;

    private Float latitude;
    private Float longitude;
}
