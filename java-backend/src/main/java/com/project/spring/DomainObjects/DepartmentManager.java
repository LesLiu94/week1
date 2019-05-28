package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "employeesschema", name = "dept_manager")
public class DepartmentManager{
    //int dept_no, int emp_no, date from_date, date to_date
    @Column(name = "dept_no")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    private int deptNo;

    @Column(name = "emp_no")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    private int empNo;

    @Column(name = "from_date")
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private Date fromDate;

    @Column(name = "to_date")
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private Date toDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no")
    @NotBlank
    private Department department;

    //Getters and Setters

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
