package com.project.spring.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.spring.Enums.EmployeeTitle;
import com.project.spring.Enums.Sex;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class EmployeeResult {

    private String firstName;
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date dob;
    private EmployeeTitle employeeTitle;
    private List<String> departments;
    private Double salary;
    private Integer empNo;

    //this boolean is the frontend's lookup html
    private boolean isActive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date hireDate;

    private Sex gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date fromDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    @NotNull
    private Date toDate;

    private boolean isUnequallyPaid = false;

    @Override
    public String toString() {
        String departmentsString = String.join(", ", departments);
        String resultString = String.format("%s %s is a(n) %s with department(s): %s with a salary of %f and " +
                "hired on %s", this.getFirstName(), this.getLastName(), this.getEmployeeTitle(), departmentsString,
                this.getSalary(), this.getHireDate());
        return resultString;
    }

    //Getters and Setters

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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public EmployeeTitle getEmployeeTitle() {
        return employeeTitle;
    }

    public void setEmployeeTitle(EmployeeTitle employeeTitle) {
        this.employeeTitle = employeeTitle;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isUnequallyPaid() {
        return isUnequallyPaid;
    }

    public void setUnequallyPaid(boolean unequallyPaid) {
        isUnequallyPaid = unequallyPaid;
    }
}
