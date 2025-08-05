package com.deutsche.demo;

import com.deutsche.demo.exception.EmployeeNotFoundException;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import com.deutsche.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;

	@Test
	void testGetEmployeeById_Success() {
		Employee emp = new Employee(101, "Neha", 50000.0);
		when(employeeRepository.findById(101)).thenReturn(Optional.of(emp));
		Employee result = employeeService.getEmployeeById(101);
		assertEquals("Neha", result.getName());
	}

	@Test
	void testGetEmployeeById_NotFound() {
		when(employeeRepository.findById(999)).thenReturn(Optional.empty());
		assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(999));
	}

	@Test
	void testAddEmployee_Success() {
		Employee emp = new Employee(102, "John", 60000.0);
		when(employeeRepository.existsById(102)).thenReturn(false);
		when(employeeRepository.save(emp)).thenReturn(emp);
		Employee result = employeeService.addEmployee(emp);
		assertEquals("John", result.getName());
	}

	@Test
	void testUpdateEmployee_NotFound() {
		Employee emp = new Employee(105, "Alice", 70000.0);
		when(employeeRepository.existsById(105)).thenReturn(false);
		assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateEmployee(emp));
	}

	@Test
	void testDeleteEmployee_Success() {
		Employee emp = new Employee(106, "Leo", 45000.0);
		when(employeeRepository.findById(106)).thenReturn(Optional.of(emp));
		doNothing().when(employeeRepository).deleteById(106);
		Employee deleted = employeeService.deleteEmployee(106);
		assertEquals("Leo", deleted.getName());
	}
}
