package com.footsalon.location;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Entity
public class Location {
    @Id
    private String localCode;

    private String localCity;

    public static Location createLocation(Location request) {
        return Location.builder()
                .localCode(request.getLocalCode())
                .localCity(request.getLocalCity())
                .build();
    }
}
