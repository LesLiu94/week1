package com.project.spring.DomainObjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.spring.CompositeKeys.SalariesCompositeKey;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "employeesschema", name = "salaries")
public class Salary implements Serializable {

    @EmbeddedId
    private SalariesCompositeKey salariesCompositeKey;

    @Column(name = "salary")
    @NotNull(message = "Please enter pay")
    private double pay;

    @Column(name = "to_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date toDate;

    public Salary() {
        salariesCompositeKey = new SalariesCompositeKey();
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public Date getFromDate() {
        return (salariesCompositeKey==null) ? null : salariesCompositeKey.getFromDate();
    }

    public void setFromDate(Date fromDate) {
        if (salariesCompositeKey != null) {
            salariesCompositeKey.setFromDate(fromDate);
        }
    }

    public Employee getEmployee() {
        return (salariesCompositeKey==null) ? null : salariesCompositeKey.getEmployee();
    }

    public void setEmployee(Employee employee) {
        if(salariesCompositeKey != null){
            salariesCompositeKey.setEmployee(employee);
        }
    }

    /*public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }*/

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
