package com.project.spring.Services;

import com.project.spring.DomainObjects.Employee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class UnequalPayLookupUtilities {

    static final Comparator<Employee> sortByHireDateComparator = Comparator.comparing(Employee::getHireDate);

}


