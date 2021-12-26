package com.footsalon.notice;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Notice {

    @Id
    private long ntIdx;
    private String ntTitle;
    private String ntContent;
    private LocalDateTime regDate;
    private String delYn;
    private int ntMain;
    private int views;
}
