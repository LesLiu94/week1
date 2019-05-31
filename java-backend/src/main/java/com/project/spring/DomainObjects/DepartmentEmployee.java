package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.spring.CompositeKeys.DeptEmpCompositeKey;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(schema = "employeesschema", name = "dept_emp")
public class DepartmentEmployee implements Serializable{

    @EmbeddedId
    private DeptEmpCompositeKey deptEmpCompositeKey;

    @Column(name = "emp_no",insertable=false, updatable=false)
    @NotBlank
    private int empNo;

    @Column(name = "dept_no",insertable=false, updatable=false)
    @NotBlank
    private int deptNo;

    @Column(name = "from_date")
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private Date fromDate;

    @Column(name = "to_date")
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
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

    public DeptEmpCompositeKey getDeptEmpCompositeKey() {
        return deptEmpCompositeKey;
    }

    public void setDeptEmpCompositeKey(DeptEmpCompositeKey deptEmpCompositeKey) {
        this.deptEmpCompositeKey = deptEmpCompositeKey;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
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
