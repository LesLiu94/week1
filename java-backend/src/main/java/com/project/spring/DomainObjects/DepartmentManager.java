package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.spring.CompositeKeys.DeptEmpCompositeKey;
import com.project.spring.CompositeKeys.DeptManagerCompositeKey;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(schema = "employeesschema", name = "dept_manager")
public class DepartmentManager implements Serializable{

    @EmbeddedId
    private DeptManagerCompositeKey deptManagerCompositeKey;

    @Column(name = "emp_no", insertable = false, updatable = false)
    @NotBlank
    private int empNo;

    @Column(name = "dept_no", insertable = false, updatable = false)
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
    private Department departments;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    @NotBlank
    private Employee employee;

    //Getters and Setters

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

    public DeptManagerCompositeKey getDeptManagerCompositeKey() {
        return deptManagerCompositeKey;
    }

    public void setDeptManagerCompositeKey(DeptManagerCompositeKey deptManagerCompositeKey) {
        this.deptManagerCompositeKey = deptManagerCompositeKey;
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

    public Department getDepartments() {
        return departments;
    }

    public void setDepartments(Department departments) {
        this.departments = departments;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
