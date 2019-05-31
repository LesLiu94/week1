package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.spring.Enums.Sex;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

@Entity
@Table(schema = "employeesschema", name = "employees")
public class Employee{

    @Column(name = "emp_no")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    private int empNo;

    @Column(name = "birth_date")
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private String birthDate;

    @Column(name = "first_name")
    @NotBlank
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    private String lastName;

    @Column(name = "gender")
    @NotBlank
    @Enumerated
    private Sex sex;

    @Column(name = "hire_date")
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date hireDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no",insertable=false,updatable=false)
    @NotBlank
    private Set<Salary> salaries;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no",insertable=false,updatable=false)
    @NotBlank
    private Set<Title> titles;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no",insertable=false,updatable=false)
    @NotBlank
    private Set<DepartmentManager> departmentManager;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no",insertable=false,updatable=false)
    @NotBlank
    private Set<DepartmentEmployee> departmentEmployee;

    @Override
    public String toString(){
        String resultString = String.join(" ",this.firstName, this.lastName, this.birthDate);
        return resultString;
    }

    //Getters and Setters

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Set<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(Set<Salary> salaries) {
        this.salaries = salaries;
    }

    public Set<Title> getTitles() {
        return titles;
    }

    public void setTitles(Set<Title> titles) {
        this.titles = titles;
    }

    public Set<DepartmentManager> getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(Set<DepartmentManager> departmentManager) {
        this.departmentManager = departmentManager;
    }

    public Set<DepartmentEmployee> getDepartmentEmployee() {
        return departmentEmployee;
    }

    public void setDepartmentEmployee(Set<DepartmentEmployee> departmentEmployee) {
        this.departmentEmployee = departmentEmployee;
    }
}
