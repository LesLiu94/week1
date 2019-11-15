package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.spring.CompositeKeys.DeptManagerCompositeKey;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

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

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
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
