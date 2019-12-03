package com.project.spring.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.spring.Enums.EmployeeTitle;

import java.util.Date;
import java.util.List;

public class EmployeeLookupResult {

    private String firstName;
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date dob;
    private EmployeeTitle employeeTitle;
    private List<String> departments;
    private Double salary;
    private Integer empNo;

    @Override
    public String toString() {
        String departmentsString = String.join(", ", departments);
        String resultString = String.format("%s %s is a(n) %s with department(s): %s with a salary of $%f", firstName, lastName, employeeTitle, departmentsString, salary);
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
}
