package com.project.spring.CompositeKeys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.spring.DomainObjects.Employee;
import com.project.spring.Enums.EmployeeTitle;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class TitlesCompositeKey implements Serializable {

    /*@Column(name = "emp_no")
    private int empNo;*/

    @ManyToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Employee employee;

    /*@Column(name = "title")
    @Enumerated(EnumType.STRING)
    private EmployeeTitle title;*/

    @Column(name = "title")
    @NotNull
    @Enumerated(EnumType.STRING)
    @Type(type="psql_enum")
    private EmployeeTitle title;

    @Column(name = "from_date")
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private Date fromDate;

    public TitlesCompositeKey(){
    }

    public TitlesCompositeKey(Employee employee, EmployeeTitle title, Date fromDate){
        this.employee = employee;
        this.title = title;
        this.fromDate = fromDate;
    }

    /*public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }*/

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeTitle getTitle() {
        return title;
    }

    public void setTitle(EmployeeTitle title) {
        this.title = title;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TitlesCompositeKey that = (TitlesCompositeKey) o;

        if (employee.getEmpNo() != that.employee.getEmpNo()) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return fromDate != null ? fromDate.equals(that.fromDate) : that.fromDate == null;

    }

    @Override
    public int hashCode() {
        int result = employee.getEmpNo();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        return result;
    }
}
