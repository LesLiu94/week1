package com.project.spring.DTO;

import com.project.spring.Enums.EmployeeTitle;

public class EditEmployee {

    private String firstName;
    private String lastName;
    private EmployeeTitle title;
    private Double salary;
    private Integer empNo;

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

    public EmployeeTitle getTitle() {
        return title;
    }

    public void setTitle(EmployeeTitle title) {
        this.title = title;
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
