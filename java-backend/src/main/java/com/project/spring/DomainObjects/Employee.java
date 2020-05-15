package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.spring.Enums.Sex;
import com.project.spring.Misc.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "employeesschema", name = "employees")
@TypeDef(
        name = "psql_enum",
        typeClass = PostgreSQLEnumType.class
)
@SequenceGenerator(name="emp_seq",
        sequenceName="emp_seq", schema="employeesschema")

public class Employee{

    @Id
    @Column(name = "emp_no")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="emp_seq")
    @NotNull
    private Integer empNo;

    @Column(name = "birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    @NotNull
    private Date birthDate;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(columnDefinition = "sex", name = "gender")
    @NotNull
    @Enumerated(EnumType.STRING)
    @Type(type="psql_enum")
    private Sex sex;

    @Column(name = "hire_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    @NotNull
    private Date hireDate;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "emp_no")
    @NotNull
    private List<Salary> salaries;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "emp_no")
    @NotNull
    private List<Title> titles;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no",insertable=false,updatable=false)
    private List<DepartmentManager> departmentManager;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no",insertable=false,updatable=false)
    private List<DepartmentEmployee> departmentEmployee;

    @Override
    public String toString(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dobString = dateFormat.format(this.birthDate);
        String resultString = String.join(" ",this.firstName, this.lastName, dobString);
        return resultString;
    }

    //Getters and Setters
    public Integer getEmpNo() {
        return empNo;
    }

    //EmpNo setter used for testing
    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public List<DepartmentManager> getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(List<DepartmentManager> departmentManager) {
        this.departmentManager = departmentManager;
    }

    public List<DepartmentEmployee> getDepartmentEmployee() {
        return departmentEmployee;
    }

    public void setDepartmentEmployee(List<DepartmentEmployee> departmentEmployee) {
        this.departmentEmployee = departmentEmployee;
    }
}
