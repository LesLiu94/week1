package com.project.spring.Endpoints;

import com.project.spring.Endpoint.UnequalPayEndpoint;
import com.project.spring.Enums.EmployeeTitle;
import com.project.spring.Services.UnequalPayLookupService;
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
import java.util.Date;
import java.util.List;

import static com.project.spring.Enums.EmployeeTitle.EMPLOYEE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UnequalPayEndpointTest {

    private MockMvc mockMvc;

    @Mock
    UnequalPayLookupService unequalPayLookupService;

    @InjectMocks
    UnequalPayEndpoint unequalPayEndpoint;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(unequalPayEndpoint)
                .build();
    }

    @Test
    public void testFindUnequallyPaidEmployees() throws Exception{

        UnequalLookupResult unequalLookupResultStub = new UnequalLookupResult();
        EmployeeTitle employeeTitleStub = EMPLOYEE;
        Double salaryStub = 1.00;
        List<String> departmentStub = new ArrayList<>();
        departmentStub.add("MARKETING");
        Date hireDateStub = new Date();
        unequalLookupResultStub.setFirstName("Jane");
        unequalLookupResultStub.setLastName("Doe");
        unequalLookupResultStub.setSalary(salaryStub);
        unequalLookupResultStub.setDepartments(departmentStub);
        unequalLookupResultStub.setEmployeeTitle(employeeTitleStub);
        unequalLookupResultStub.setHireDate(hireDateStub);
        ArrayList<UnequalLookupResult> unequalLookupResultListStub = new ArrayList<>();
        unequalLookupResultListStub.add(unequalLookupResultStub);

        Mockito.when(unequalPayLookupService.findUnequallyPaidEmployees()).thenReturn(unequalLookupResultListStub);
        mockMvc.perform(get("/api/PayLookup/unequalEmployees").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        Mockito.verify(unequalPayLookupService).findUnequallyPaidEmployees();
        Mockito.verifyNoMoreInteractions(unequalPayLookupService);
    }
}
