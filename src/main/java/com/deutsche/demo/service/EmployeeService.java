package com.deutsche.demo.service;

import com.deutsche.demo.exception.EmployeeNotFoundException;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee addEmployee(Employee employee) {
        if (employee.getId() != null && employeeRepository.existsById(employee.getId())) {
            throw new RuntimeException("Employee with ID " + employee.getId() + " already exists.");
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        if (employee.getId() == null) {
            LOG.error("Update failed: Employee ID is null.");
            throw new IllegalArgumentException("Employee ID must not be null for update.");
        }

        Optional<Employee> existingEmp = employeeRepository.findById(employee.getId());

        if (!existingEmp.isPresent()) {
            LOG.warn("Update failed: Employee with ID {} not found.", employee.getId());
            throw new EmployeeNotFoundException(employee.getId());
        }

        LOG.info("Updating employee with ID {}: {}", employee.getId(), employee);
        return employeeRepository.save(employee);
    }


    public List<Employee> getEmployeesByName(String name) {
        LOG.info("Fetching employees with name: {}", name);
        List<Employee> employees = employeeRepository.findByNameIgnoreCase(name);
        if (employees.isEmpty()) {
            LOG.warn("No employees found with name: {}", name);
        } else {
            LOG.info("Found {} employee(s) with name: {}", employees.size(), name);
        }
        return employees;
    }

    public Employee deleteEmployee(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return optionalEmployee.get(); // returning deleted object
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }
}

