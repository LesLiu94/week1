package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.spring.CompositeKeys.TitlesCompositeKey;
import com.project.spring.Enums.EmployeeTitle;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "employeesschema", name = "titles")

public class Title implements Serializable{

    @EmbeddedId
    private TitlesCompositeKey titlesCompositeKey;

    /*@Column(name = "emp_no",insertable=false, updatable=false)
    @NotBlank
    private int empNo;*/

    /*@Column(name = "title",insertable=false, updatable=false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private EmployeeTitle title;*/

    /*@Column(name = "from_date",insertable=false, updatable=false)
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private Date fromDate;*/

    @Column(name = "to_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private Date toDate;

    @Override
    public String toString() {
        String resultString = titlesCompositeKey.getTitle().toString();
        return resultString;
    }

    public Title(){
        titlesCompositeKey = new TitlesCompositeKey();
    }

    //Getters and Setters

    public TitlesCompositeKey getTitlesCompositeKey() {
        return titlesCompositeKey;
    }

    public void setTitlesCompositeKey(TitlesCompositeKey titlesCompositeKey) {
        this.titlesCompositeKey = titlesCompositeKey;
    }

    public Employee getEmployee() {
        return (titlesCompositeKey==null) ? null : titlesCompositeKey.getEmployee();
    }

    public void setEmployee(Employee employee) {
        if(titlesCompositeKey != null){
            titlesCompositeKey.setEmployee(employee);
        }
    }

    public EmployeeTitle getTitle() {
        return (titlesCompositeKey==null) ? null : titlesCompositeKey.getTitle();
    }

    public void setTitle(EmployeeTitle title) {
        if(titlesCompositeKey != null) {
            titlesCompositeKey.setTitle(title);
        }
    }

    public Date getFromDate() {
        return (titlesCompositeKey==null) ? null : titlesCompositeKey.getFromDate();
    }

    public void setFromDate(Date fromDate) {
        if(titlesCompositeKey != null){
            titlesCompositeKey.setFromDate(fromDate);
        }
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
