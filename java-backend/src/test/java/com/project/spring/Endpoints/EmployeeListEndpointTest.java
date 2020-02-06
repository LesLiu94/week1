package com.project.spring.Endpoints;

import com.project.spring.DTO.EmployeeResult;
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

        EmployeeResult employeeResultStub = new EmployeeResult();
        EmployeeTitle employeeTitleStub = EMPLOYEE;
        Double salaryStub = 1.00;
        List<String> departmentStub = new ArrayList<>();
        departmentStub.add("MARKETING");
        employeeResultStub.setFirstName("Jane");
        employeeResultStub.setLastName("Doe");
        employeeResultStub.setSalary(salaryStub);
        employeeResultStub.setDepartments(departmentStub);
        employeeResultStub.setEmployeeTitle(employeeTitleStub);
        List<EmployeeResult> employeeResultListStub = new ArrayList<>();
        employeeResultListStub.add(employeeResultStub);

        Mockito.when(employeeListLookupService.findAllEmplyees()).thenReturn(employeeResultListStub);
        mockMvc.perform(get("/api/EmployeeListLookup/allEmployees").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        Mockito.verify(employeeListLookupService).findAllEmplyees();
        Mockito.verifyNoMoreInteractions(employeeListLookupService);
    }
}
