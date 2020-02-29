package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "employeesschema", name = "dept_emp")
@SequenceGenerator(name="dept_emp_seq",
        sequenceName="dept_emp_seq", schema="employeesschema")


public class DepartmentEmployee implements Serializable{

    @Id
    @Column(name = "dept_emp_no")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="dept_emp_seq")
    private int deptEmpNo;

    @Column(name = "emp_no",insertable=false, updatable=false)
    @NotNull
    private int empNo;

    @Column(name = "dept_no",insertable=false, updatable=false)
    @NotBlank
    private String deptNo;

    @Column(name = "from_date")
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date fromDate;

    @Column(name = "to_date")
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date toDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no", insertable = false, updatable = false)
    @NotBlank
    private Department department;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    @NotBlank
    private Employee employee;

    //Getters and Setters


    public int getDeptEmpNo() {
        return deptEmpNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
