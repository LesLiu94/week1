package com.project.spring.Services;

import com.project.spring.Endpoint.UnequalPayEndpoint;
import com.project.spring.Enums.EmployeeTitle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UnequalLookupResult extends EmployeeLookupResult {


    private String hireDate;

    final static Logger logger = LogManager.getLogger(UnequalPayEndpoint.class);

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
        return LocalDate.fromDateFields(super.getDob()).toString("MM/dd/yyyy");
    }

    public void setBirthDate(String birthDate) {
        try{

            Date dob =new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
            super.setDob(dob);
        }catch(ParseException e){
            logger.info("The birthdate could not be properly parsed.");
            super.setDob(null);
        }
    }

}

