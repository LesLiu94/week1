package com.project.spring.Endpoints;

import com.project.spring.DTO.EmployeeLookupResult;
import com.project.spring.Endpoint.EmployeeListEndpoint;
import com.project.spring.Enums.EmployeeTitle;
import com.project.spring.Services.EmployeeListLookupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

import static com.project.spring.Enums.EmployeeTitle.EMPLOYEE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeListEndpointTest {

    private MockMvc mockMvc;

    @Mock
    EmployeeListLookupService employeeListLookupService;

    @InjectMocks
    EmployeeListEndpoint employeeListEndpoint;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(employeeListEndpoint)
                .build();
    }

    @Test
    public void testFindAllEmployees() throws Exception {

        EmployeeLookupResult employeeLookupResultStub = new EmployeeLookupResult();
        EmployeeTitle employeeTitleStub = EMPLOYEE;
        Double salaryStub = 1.00;
        List<String> departmentStub = new ArrayList<>();
        departmentStub.add("MARKETING");
        employeeLookupResultStub.setFirstName("Jane");
        employeeLookupResultStub.setLastName("Doe");
        employeeLookupResultStub.setSalary(salaryStub);
        employeeLookupResultStub.setDepartments(departmentStub);
        employeeLookupResultStub.setEmployeeTitle(employeeTitleStub);
        List<EmployeeLookupResult> employeeLookupResultListStub = new ArrayList<>();
        employeeLookupResultListStub.add(employeeLookupResultStub);

        Mockito.when(employeeListLookupService.findAllEmplyees()).thenReturn(employeeLookupResultListStub);
        mockMvc.perform(get("/api/EmployeeListLookup/allEmployees").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        Mockito.verify(employeeListLookupService).findAllEmplyees();
        Mockito.verifyNoMoreInteractions(employeeListLookupService);
    }
}
