package com.footsalon.location;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Location {
    @Id
    private String localCode;
    private String localCity;
}
