package com.deutsche.demo.controller;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins ="http://localhost:5173")
@RestController
@RequestMapping("api")
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    // GET all employees
    // http://localhost:8080/api/v1/api/emp
    @GetMapping("emp")
    public List<Employee> getAllEmployees() {
        return empService.getAllEmployees();
    }

    // GET employee by ID
    // http://localhost:8080/api/emp/101
    @GetMapping("emp/{id}")
    public Employee getEmployeeById(@PathVariable("id") Integer id) {
        if(empService.getEmployeeById(id)!=null)
            return empService.getEmployeeById(id);
        else{
            System.out.println("Employee not found");
            return null;
        }
    }

    // POST - Add new employee
    // http://localhost:8080/api/emp
    @PostMapping("emp")
    public Employee addEmployee(@RequestBody Employee employee) {
        return empService.addEmployee(employee);
    }

    // DELETE - Delete employee by ID
    // http://localhost:8080/api/emp/101
    @DeleteMapping("emp/{id}")
    public Employee deleteEmployee(@PathVariable("id") Integer id) {
        return empService.deleteEmployee(id);
    }

    // PUT http://localhost:8080/api/emp
    @PutMapping("emp")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return empService.updateEmployee(employee);
    }

    @GetMapping("/emp/name/{name}")
    public List<Employee> getEmployeesByName(@PathVariable String name) {
        return empService.getEmployeesByName(name);
    }

}
