package com.project.spring.Endpoints;

import com.project.spring.DTO.EmployeeResult;
import com.project.spring.Endpoint.EmployeeEndpoint;
import com.project.spring.Enums.EmployeeTitle;
import com.project.spring.Services.EmployeeLookupService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeEndpointTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeLookupService employeeLookupService;

    @InjectMocks
    private EmployeeEndpoint employeeEndpoint;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(employeeEndpoint)
                .build();
    }

    @Test
    public void testFindEmployeeWithUnknownEmployee() throws Exception{
        String unknownFirstNameStub = "abc";
        String unknownLastNameStub = "def";

        Mockito.when(employeeLookupService.findEmployee(unknownFirstNameStub,unknownLastNameStub)).thenReturn(null);
        mockMvc.perform(get("/api/EmployeeLookup/findEmployee").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNoContent());
        Mockito.verify(employeeLookupService).findEmployee(unknownFirstNameStub,unknownLastNameStub);
        Mockito.verifyNoMoreInteractions(employeeLookupService);

    }

    @Test
    public void testFindEmployeeWithKnownEmployee() throws  Exception{

        String knownFirstNameStub = "John";
        String knownLastNameStub = "Smith";
        EmployeeTitle employeeTitleStub = EMPLOYEE;
        List<String> departmentsStub = new ArrayList<>();
        departmentsStub.add("Human Resources");
        Double salaryStub = 1.00;

        EmployeeResult employeeResultStub = new EmployeeResult();
        employeeResultStub.setFirstName(knownFirstNameStub);
        employeeResultStub.setLastName(knownLastNameStub);
        employeeResultStub.setEmployeeTitle(employeeTitleStub);
        employeeResultStub.setDepartments(departmentsStub);
        employeeResultStub.setSalary(salaryStub);
        ArrayList<EmployeeResult> employeeResultListStub = new ArrayList<>();
        employeeResultListStub.add(employeeResultStub);

        Mockito.when(employeeLookupService.findEmployee(knownFirstNameStub, knownLastNameStub)).thenReturn(employeeResultListStub);
        mockMvc.perform(get("/api/EmployeeLookup/findEmployee").accept(MediaType.APPLICATION_JSON)
                .param("first", knownFirstNameStub)
                .param("last", knownLastNameStub))
                .andExpect(status().isOk());
        Mockito.verify(employeeLookupService).findEmployee(knownFirstNameStub,knownLastNameStub);
        Mockito.verifyNoMoreInteractions(employeeLookupService);

    }

}
