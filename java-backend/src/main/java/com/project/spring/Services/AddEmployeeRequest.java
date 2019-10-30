package com.project.spring.Services;

import com.project.spring.Enums.Sex;

import java.util.Date;

public class AddEmployeeRequest extends UnequalLookupResult{

    private Sex gender;
    private String fromDate;
    private String toDate;

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
