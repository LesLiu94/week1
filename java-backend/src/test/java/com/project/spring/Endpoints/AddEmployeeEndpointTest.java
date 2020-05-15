package com.project.spring.Endpoints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.spring.DTO.EmployeeResult;
import com.project.spring.Endpoint.AddEmployeeEndpoint;
import com.project.spring.Enums.EmployeeTitle;
import com.project.spring.Enums.Sex;
import com.project.spring.Services.AddEmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.project.spring.Enums.EmployeeTitle.EMPLOYEE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AddEmployeeEndpointTest {

    private MockMvc mockMvc;

    @Mock
    AddEmployeeService addEmployeeService;

    @InjectMocks
    AddEmployeeEndpoint addEmployeeEndpoint;

    @Captor
    private ArgumentCaptor<EmployeeResult> employeeResultBodyArgCaptor;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(addEmployeeEndpoint)
                .build();
    }

    @Test
    public void testAddEmployee() throws Exception{

        EmployeeResult addEmployeeRequestStub = new EmployeeResult();
        String firstNameStub = "John";
        String lastNameStub = "Smith";
        EmployeeTitle employeeTitleStub = EMPLOYEE;
        List<String> departmentsStub = new ArrayList<>();
        departmentsStub.add("Marketing");
        Double salaryStub = 1.00;
        Date hireDateStub = new Date();
        addEmployeeRequestStub.setFirstName(firstNameStub);
        addEmployeeRequestStub.setLastName(lastNameStub);
        addEmployeeRequestStub.setEmployeeTitle(employeeTitleStub);
        addEmployeeRequestStub.setDepartments(departmentsStub);
        addEmployeeRequestStub.setSalary(salaryStub);
        addEmployeeRequestStub.setHireDate(hireDateStub);
        addEmployeeRequestStub.setGender(Sex.M);

        EmployeeResult employeeResultStub = new EmployeeResult();
        employeeResultStub.setEmpNo(3);
        employeeResultStub.setDepartments(departmentsStub);
        employeeResultStub.setSalary(salaryStub);
        employeeResultStub.setEmployeeTitle(employeeTitleStub);
        employeeResultStub.setFirstName(firstNameStub);
        employeeResultStub.setLastName(lastNameStub);
        Date dobStub = new Date();
        employeeResultStub.setDob(dobStub);

        Gson gson = new GsonBuilder()
                .setDateFormat("MM/dd/yyyy").create();

        String jsonStub = gson.toJson(addEmployeeRequestStub);

        Mockito.when(addEmployeeService.addEmployee(employeeResultBodyArgCaptor.capture())).thenReturn(employeeResultStub);

        mockMvc.perform(post("/api/AddEmployee/employee").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStub))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        EmployeeResult actualEmployeeRequest = employeeResultBodyArgCaptor.getValue();
        Assert.assertEquals(addEmployeeRequestStub.getSalary(), actualEmployeeRequest.getSalary());
        Mockito.verify(addEmployeeService).addEmployee(actualEmployeeRequest);
        Mockito.verifyNoMoreInteractions(addEmployeeService);

    }

}
