package com.project.spring.Services;

import com.project.spring.Enums.EmployeeTitle;

import java.util.Date;
import java.util.List;

public class UnequalLookupResult extends EmployeeLookupResult {

    private String birthDate;
    private String hireDate;

    @Override
    public String toString() {
        String resultString = String.format("%s %s is an unequally paid employee who was hired on %s with a salary of $%f", this.getFirstName(), this.getLastName(), this.getHireDate(), this.getSalary());
        return resultString;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

}

