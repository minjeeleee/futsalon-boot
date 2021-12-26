package com.footsalon.match;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class MatchGame {

    @Id
    @GeneratedValue
    private long mgIdx;

    @ManyToOne
    @JoinColumn(name = "mmIdx")
    private MatchMaster matchMaster;

    private int state;
    private String applicantCode;
    private String userId;
    private String delYn;

}
