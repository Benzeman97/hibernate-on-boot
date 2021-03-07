package com.benz.hib.jpa.api.entity;

import com.benz.hib.jpa.api.db.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT",schema = Schema.TESTDB)
@Getter
@Setter
public class Student {

    @Id
    @Column(name = "STU_ID")
    private int stuId;
    @Column(name = "STU_NAME",nullable = false)
    private String stuName;
    @Column(name = "SALARY")
    private double salary;
}
