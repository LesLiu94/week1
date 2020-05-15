package com.project.spring.Misc;

import com.project.spring.DomainObjects.Employee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class UnequalPayLookupUtilities {

    public static final Comparator<Employee> sortByHireDateComparator = Comparator.comparing(Employee::getHireDate);

}


