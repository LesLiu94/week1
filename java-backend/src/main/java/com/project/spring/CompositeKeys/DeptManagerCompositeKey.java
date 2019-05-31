package com.project.spring.CompositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DeptManagerCompositeKey implements Serializable {
    @Column(name = "emp_no", insertable = false, updatable = false)
    private int empNo;

    @Column(name = "dept_no", insertable = false, updatable = false)
    private int deptNo;

    public DeptManagerCompositeKey(){
    }

    public DeptManagerCompositeKey(int empNo, int deptNo){
        this.empNo = empNo;
        this.deptNo = deptNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public int getDeptNo() {
        return deptNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeptManagerCompositeKey that = (DeptManagerCompositeKey) o;

        if (empNo != that.empNo) return false;
        return deptNo == that.deptNo;

    }

    @Override
    public int hashCode() {
        int result = empNo;
        result = 31 * result + deptNo;
        return result;
    }
}
