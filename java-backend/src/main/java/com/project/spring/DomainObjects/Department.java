package com.project.spring.DomainObjects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "employeesschema", name = "departments")
public class Department {
    //int dept_no, String dept_name
    @Column(name = "dept_no")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String deptNo;

    @Column(name = "dept_name", unique = true)
    @NotNull
    private String deptName;

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
