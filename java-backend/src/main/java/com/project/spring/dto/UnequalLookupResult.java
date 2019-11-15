package com.project.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.spring.Endpoint.UnequalPayEndpoint;
import com.project.spring.dto.EmployeeLookupResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;

import java.util.Date;

public class UnequalLookupResult extends EmployeeLookupResult {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date hireDate;

    final static Logger logger = LogManager.getLogger(UnequalPayEndpoint.class);

    @Override
    public String toString() {
        String resultString = String.format("%s %s is an unequally paid employee who was hired on %s with " +
                "a salary of $%f", this.getFirstName(), this.getLastName(), this.getHireDate(), this.getSalary());
        return resultString;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    public Date getBirthDate() {
        return super.getDob();
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    public void setBirthDate(Date birthDate) {
        super.setDob(birthDate);
    }

}

