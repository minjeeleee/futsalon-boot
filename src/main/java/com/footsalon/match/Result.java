package com.footsalon.match;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Result {

    @Id
    private long thIdx;
    private long mgIdx;
    private int rivalRating;
    private int hostRating;
    private String winner;
}
