package com.project.spring.CompositeKeys;

import com.project.spring.Enums.EmployeeTitle;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class TitlesCompositeKey implements Serializable {

    @Column(name = "emp_no")
    private int empNo;

    @Column(name = "title")
    @Enumerated(EnumType.STRING)
    private EmployeeTitle title;

    @Column(name = "from_date")
    private Date fromDate;

    public TitlesCompositeKey(){
    }

    public TitlesCompositeKey(int empNo, EmployeeTitle title, Date fromDate){
        this.empNo = empNo;
        this.title = title;
        this.fromDate = fromDate;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
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

        if (empNo != that.empNo) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return fromDate != null ? fromDate.equals(that.fromDate) : that.fromDate == null;

    }

    @Override
    public int hashCode() {
        int result = empNo;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        return result;
    }
}
