package com.footsalon.match;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class MatchGame {

    @Id
    private long mgIdx;

    @ManyToOne
    @JoinColumn(name = "mmIdx")
    private MatchMaster matchMaster;

    private int state;
    private String applicantCode;
    private String userId;
    private String delYn;
}
