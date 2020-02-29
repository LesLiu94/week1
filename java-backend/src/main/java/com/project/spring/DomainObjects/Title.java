package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.spring.Enums.EmployeeTitle;
import com.project.spring.Misc.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "employeesschema", name = "titles")
@TypeDef(
        name = "psql_enum",
        typeClass = PostgreSQLEnumType.class
)
@SequenceGenerator(name="title_seq",
        sequenceName="title_seq", schema="employeesschema")

public class Title implements Serializable{

    @Id
    @Column(name = "title_no")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="title_seq")
    private Integer titleNo;

    @Column(name = "emp_no",insertable=false, updatable=false)
    @NotNull
    private int empNo;

    @Column(columnDefinition = "title", name = "title")
    @NotNull
    @Enumerated(EnumType.STRING)
    @Type(type="psql_enum")
    private EmployeeTitle title;

    @Column(name = "from_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date fromDate;

    @Column(name = "to_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date toDate;

    //Getters and Setters


    public Integer getTitleNo() {
        return titleNo;
    }

    public int getEmpNo() {
        return empNo;
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
