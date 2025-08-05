package com.deutsche.demo;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.service.EmployeeService;
import com.deutsche.demo.controller.EmployeeController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Neha", 50000.0),
                new Employee(2, "Amit", 60000.0)
        );
        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/api/emp"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testAddEmployee() throws Exception {
        Employee emp = new Employee(3, "Ravi", 55000.0);
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(emp);

        mockMvc.perform(post("/api/emp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        Employee emp = new Employee(4, "Sneha", 65000.0);
        when(employeeService.deleteEmployee(4)).thenReturn(emp);

        mockMvc.perform(delete("/api/emp/4"))
                .andExpect(status().isOk());
    }
}
