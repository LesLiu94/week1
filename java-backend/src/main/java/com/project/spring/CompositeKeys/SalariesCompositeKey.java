package com.project.spring.CompositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class SalariesCompositeKey implements Serializable {
    @Column(name = "emp_no")
    private int empNo;

    @Column(name = "from_date")
    private Date fromDate;

    public SalariesCompositeKey(){
    }

    public SalariesCompositeKey(int empNo, Date fromDate){
        this.empNo = empNo;
        this.fromDate = fromDate;
    }

    public int getEmpNo(){
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public Date getFromDate(){
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalariesCompositeKey that = (SalariesCompositeKey) o;

        if (empNo != that.empNo) return false;
        return fromDate != null ? fromDate.equals(that.fromDate) : that.fromDate == null;

    }

    @Override
    public int hashCode() {
        int result = empNo;
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        return result;
    }
}
