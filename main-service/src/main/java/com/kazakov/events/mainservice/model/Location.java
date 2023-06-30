package com.kazakov.events.mainservice.model;

import lombok.*;

import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location {
    Double lat;
    Double lon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass()  != Hibernate.unproxy(o).getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(lat, location.getLat()) && Objects.equals(lon, location.getLon());
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }
}
