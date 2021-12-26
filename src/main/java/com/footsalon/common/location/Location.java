package com.footsalon.common.location;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Location {

    @Id
    private String localCode;
    private String localCity;
}
