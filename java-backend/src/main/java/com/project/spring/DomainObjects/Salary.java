package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(schema = "employeesschema", name = "salaries")

public class Salary {

    //int emp_no, int salary, date from_date, date to_date
    @Column(name = "emp_no")
    @Id
    @NotBlank
    private int empNo;

    @Column(name = "salary")
    @NotBlank
    private int pay;

    @Column(name = "from_date")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fromDate;

    @Column(name = "to_date")
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date toDate;

    public int getEmp_No() {
        return empNo;
    }

    public void setEmp_No(int emp_No) {
        this.empNo = emp_No;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
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
