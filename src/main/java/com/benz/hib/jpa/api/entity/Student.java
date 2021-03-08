package com.benz.hib.jpa.api.entity;

import com.benz.hib.jpa.api.db.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT",schema = Schema.TESTDB)
@Getter
@Setter
public class Student {

    @Id
   /* @SequenceGenerator(name = "STU_ID_GEN",sequenceName = Schema.TESTDB+".STU_ID_SEQ",initialValue = 104,allocationSize = 1)
    @GeneratedValue(generator = "STU_ID_GEN",strategy = GenerationType.SEQUENCE)*/
    @Column(name = "STU_ID")
    private int stuId;
    @Column(name = "STU_NAME",nullable = false)
    private String stuName;
    @Column(name = "SALARY")
    private double salary;
}
