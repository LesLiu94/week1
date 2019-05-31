package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.spring.CompositeKeys.TitlesCompositeKey;
import com.project.spring.Enums.EmployeeTitle;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "employeesschema", name = "titles")

public class Title implements Serializable{

    @EmbeddedId
    private TitlesCompositeKey titlesCompositeKey;

    @Column(name = "emp_no",insertable=false, updatable=false)
    @NotBlank
    private int empNo;

    @Column(name = "title",insertable=false, updatable=false)
    @NotBlank
    @Enumerated(EnumType.STRING)
    private EmployeeTitle title;

    @Column(name = "from_date",insertable=false, updatable=false)
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private Date fromDate;

    @Column(name = "to_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private Date toDate;

    //Getters and Setters

    public TitlesCompositeKey getTitlesCompositeKey() {
        return titlesCompositeKey;
    }

    public void setTitlesCompositeKey(TitlesCompositeKey titlesCompositeKey) {
        this.titlesCompositeKey = titlesCompositeKey;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public EmployeeTitle getTitle() {
        return title;
    }

    public void setTitle(EmployeeTitle title) {
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
