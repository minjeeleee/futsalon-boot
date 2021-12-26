package com.footsalon.member;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Member {

    @Id
    private String userId;
    private String tmCode;
    private String password;
    private String userName;
    private String grade;
    private String userNick;
    private String email;
    private String tell;
    private String capacity;
    private String leaveYn;
    private LocalDateTime regDate;


}
