package com.project.spring.Utility;

import com.project.spring.DomainObjects.Employee;

import java.util.Comparator;

public class SortByHireDate implements Comparator<Employee> {
    public int compare(Employee a, Employee b){
        return a.getHireDate().compareTo(b.getHireDate());
    }
}


