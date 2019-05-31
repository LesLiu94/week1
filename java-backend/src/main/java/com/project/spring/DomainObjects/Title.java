package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "employeesschema", name = "titles")

public class Title implements Serializable{
    //int emp_no, String title, String from_date, String to_date

    @Column(name = "emp_no")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    private int empNo;

    @Column(name = "title")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    private String title;

    @Column(name = "from_date")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private Date fromDate;

    @Column(name = "to_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private Date toDate;

    //Getters and Setters

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
