package com.project.spring.Services;

import com.project.spring.DAO.*;
import com.project.spring.DTO.EmployeeResult;
import com.project.spring.DomainObjects.DepartmentEmployee;
import com.project.spring.DomainObjects.Employee;
import com.project.spring.DomainObjects.Salary;
import com.project.spring.DomainObjects.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeLookupServiceTest {

    @Mock
    private EmployeeDAO employeeDAO;

    @InjectMocks
    private EmployeeLookupService employeeLookupService;

    @Test
    public void testFindEmployeeWithUnknownEmployee(){

        List<EmployeeResult> result;
        String firstNameStub = "John";
        String lastNameStub = "Smith";

        Mockito.when(employeeDAO.findByFirstNameAndLastName(firstNameStub,lastNameStub)).thenReturn(null);
        result = employeeLookupService.findEmployee(firstNameStub,lastNameStub);
        Mockito.verify(employeeDAO).findByFirstNameAndLastName(firstNameStub,lastNameStub);
        Mockito.verifyNoMoreInteractions(employeeDAO);
        Assert.assertNull(result);

    }

    @Test
    public void testFindEmployeeWithKnownEmployee(){

        List<Employee> result = new ArrayList<>();
        List<Employee> resultStub = new ArrayList<>();
        String firstNameStub = "John";
        String lastNameStub = "Smith";
        Employee employeeStub = new Employee();
        employeeStub.setFirstName(firstNameStub);
        employeeStub.setLastName(lastNameStub);
        List<Title> titlesStub = new ArrayList<>();
        Title titleStub = new Title();
        titlesStub.add(titleStub);
        employeeStub.setTitles(titlesStub);
        List<Salary> salariesStub = new ArrayList<>();
        Salary salaryStub = new Salary();
        salaryStub.setPay(2.00);
        salariesStub.add(salaryStub);
        employeeStub.setSalaries(salariesStub);
        List<DepartmentEmployee> departmentEmployeesStub = new ArrayList<>();
        DepartmentEmployee departmentEmployeeStub = new DepartmentEmployee();
        departmentEmployeesStub.add(departmentEmployeeStub);
        employeeStub.setDepartmentEmployee(departmentEmployeesStub);
        employeeStub.setEmpNo(2);
        resultStub.add(employeeStub);

        doReturn(resultStub).when(employeeDAO.findByFirstNameAndLastName(firstNameStub,lastNameStub));
        result = employeeDAO.findByFirstNameAndLastName(firstNameStub,lastNameStub);
        Mockito.verify(employeeDAO.findByFirstNameAndLastName(firstNameStub,lastNameStub));
        Mockito.verifyNoMoreInteractions(employeeDAO);
        Assert.assertNotNull(result);


    }

}
