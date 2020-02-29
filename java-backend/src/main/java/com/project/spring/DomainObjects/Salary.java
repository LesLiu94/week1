package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "employeesschema", name = "salaries")
@SequenceGenerator(name="salary_seq",
        sequenceName="salary_seq", schema="employeesschema")

public class Salary implements Serializable {

    @Id
    @Column(name = "salary_no")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salary_seq")
    private Integer salaryNo;

    @Column(name = "emp_no")
    @NotNull
    private int empNo;

    @Column(name = "salary")
    @NotNull(message = "Please enter pay")
    private double pay;

    @Column(name = "from_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date fromDate;

    @Column(name = "to_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date toDate;

    @Column(name = "active")
    @NotNull(message = "Please enter true or false for active")
    private Boolean active;

    public Integer getSalaryNo() {
        return salaryNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
